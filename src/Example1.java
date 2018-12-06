//Created By: Dylan Cavanaugh
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

public class Example1 {

	public static void main(String[] args) {
		//Create ArrayList
		ArrayList<Integer> list = new ArrayList<Integer>();

		//Create Runnable objects to put list into
		Runnable create = new CreateArrayList(list);
		Runnable show = new IterateArrayList(list);

		//Create Threads of Runnable objects
		Thread t1 = new Thread(create);
		Thread t2 = new Thread(show);

		//Start the threads
		t1.start();
		t2.start();


	}

}

class CreateArrayList implements Runnable {

	//Initialize ArrayList and number to put in list
	ArrayList<Integer> list = new ArrayList<Integer>();
	int x = 1;

	//Constructor takes in ArrayList to manipulate
	public CreateArrayList(ArrayList<Integer> a) {
		list = a;
	}

	@Override
	public void run() {
		//Will continue to run ad infinitum because of while statement
		while(true) {
			//Adds x to the list, prints out the number was added, then increments the number
			list.add(x);
			System.out.println("Thread One add: " + x);
			x++;
			//Makes it so the thread sleeps for 1 second after writing out the value
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

class IterateArrayList implements Runnable{
	//Creates ListIterator to go through list
	ListIterator<Integer> l = null;
	ArrayList<Integer> al = new ArrayList<Integer>();

	//Constructor takes same ArrayList as above
	public IterateArrayList(ArrayList<Integer> a) {
		al = a;

	}


	@Override
	public void run() {

		//Another infinite loop to continue through
		while(true) {
			//Connects the ListIterator to the ArrayList
			l = al.listIterator();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			try {
				//Tries to output the numbers in the ArrayList
				if(l.hasNext())
				{System.out.print(l.next());
				}
			}
			//Will not be able to print numbers of the list due to this exception
			catch(ConcurrentModificationException e) {
				System.out.println("ConcurrentModificationException caught");
			}
		}
	}



}





