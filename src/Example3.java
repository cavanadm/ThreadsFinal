import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Example3 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		ExecutorService ex = Executors.newFixedThreadPool(2);
		ex.execute( new CreateArrayList2(list, 30));
		ex.execute( new IterateArrayList2(list, 30));

		
		
		ex.shutdown();
		

	}

}
class CreateArrayList2 implements Runnable {
	
	//Constructor and lists are the same as last
	ArrayList<Integer> list = new ArrayList<Integer>();
	int x = 1;
	int times = 0;

	public CreateArrayList2(ArrayList<Integer> a, int t) {
		list = a;
		times = t;
	}

	@Override
	public void run() {

		while(x < times) {

				list.add(x);			
				System.out.println("Thread One add:" + x);
				x++;
				times++;
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class IterateArrayList2 implements Runnable{
	ListIterator<Integer> l = null;
	ArrayList<Integer> al = new ArrayList<Integer>();
	int times = 0;

	public IterateArrayList2(ArrayList<Integer> a, int t) {
		al = a;
		times = t;

	}


	@Override
	public void run() {
		while(true) {
				l = al.listIterator();

				if(l.hasNext()) {
					
					while(l.hasNext()) {
						System.out.println(l.next());
						
					}
				}
				if(times > 30) {
					break;
				}
				
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
