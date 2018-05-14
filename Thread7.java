import java.util.LinkedList;
import java.util.Scanner;

/** 
 * This class is to demo wait and notify methods
 * @author chianil
 *
 */
class ProducedConsumerProcessor {
	private LinkedList<Integer> lst=new LinkedList<>();
	private final int LIMIT=10;
	Object lock=new Object();
	public  void produce() throws InterruptedException{
		synchronized (lock) {
			System.out.println("Starting Producer....");

			int c=0;
			while(c<=10000){

				while(LIMIT==lst.size()){
					// wait() can only be called from synchronized block.
					//.wait() will stop the execution of the thread by removing the lock on 'this', till a notify is called
					//---1
					lock.wait();
				}

				lst.add(c++);
				lock.notify();

			}


		}
	}

	public  void consume() throws InterruptedException{

		synchronized (lock) {
			int value;
			while(true){
				value=lst.removeFirst();
				System.out.print("Consumed "+value);
				// as soon as enter is pressed, notify is invoked to notify the thread to lock again.
				//--1
				lock.notify();
				System.out.println("; New List size is"+lst.size());
				while(lst.isEmpty()){
				lock.wait();
				}
				Thread.sleep(500);
			}
		}
	}
}

public class Thread7{
	public static void main(String [] args){
		final ProducedConsumerProcessor p=new ProducedConsumerProcessor();
		Thread t1=new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					p.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		Thread t2=new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					p.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}