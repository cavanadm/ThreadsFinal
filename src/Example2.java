//Created By: Dylan Cavanaugh
import java.util.ArrayList;
import java.util.ListIterator;


public class Example2 {

	public static void main(String[] args) {

		//All of this is exactly the same as the first example
		ArrayList<Integer> list = new ArrayList<Integer>();

		Runnable create = new CreateArrayList1(list);
		Runnable show = new IterateArrayList1(list);

		Thread t1 = new Thread(create);
		Thread t2 = new Thread(show);

		t1.start();
		t2.start();


	}

}

class CreateArrayList1 implements Runnable {
	
	//Constructor and lists are the same as last
	ArrayList<Integer> list = new ArrayList<Integer>();
	int x = 1;

	public CreateArrayList1(ArrayList<Integer> a) {
		list = a;
	}

	@Override
	public void run() {

		while(true) {
			//All of this is the same, except for the synchronized to hold the 
			//thread until finished writing the number
			synchronized(list){
				list.add(x);			
				System.out.println("Thread One add:" + x);
				x++;
			}
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class IterateArrayList1 implements Runnable{
	ListIterator<Integer> l = null;
	ArrayList<Integer> al = new ArrayList<Integer>();

	public IterateArrayList1(ArrayList<Integer> a) {
		al = a;

	}


	@Override
	public void run() {
		while(true) {
			//Also the same except with the synchronized
			synchronized(al) {
				l = al.listIterator();


				if(l.hasNext()) {	
					while(l.hasNext()) {
						System.out.println(l.next());
					}
				}
			}
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
