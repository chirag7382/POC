
public class Thread1 {


	public static void main(String[] args) {
		/**
		 * thread.start() method starts a new OS process for a thread. calling run() method will 
		 * run the thread anyway. But using start, we can run threads concurrently (simultaneously)
		 */
		
		//running multiple Threads using extended Thread Class way (Method 1)
		/*
		
		 * 
		ExtendThreadRunner r1=new ExtendThreadRunner();
		ExtendThreadRunner r2=new ExtendThreadRunner();
		r1.start();
		r2.start();
		
		*/
		
		// Running multiple threads using Runnable Interface
		
		/*
		Thread t1=new Thread(new RunnableThreadRunner());
		Thread t2=new Thread(new RunnableThreadRunner());
		
		t1.start();
		t2.start();
		
		*/
		
		// Method 3 : Running a thread within a method, without any new class using Runnable method
		
		Thread t1=new Thread(new Runnable(){

			@Override
			public void run() {
				// This method will have some code to run on thread
				for(int i=0;i<=10;i++){
					System.out.println(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
			
		}
		);
		
		t1.start();
		
	}


}
// Method 1  to start a thread : Extend Thread Class
class ExtendThreadRunner extends Thread{

	@Override
	public void run() {
		// This method will have some code to run on thread
		for(int i=0;i<=10;i++){
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
// Method 2 : Implementing Runnable Interface
class RunnableThreadRunner implements Runnable{

	@Override
	public void run() {
		// This method will have some code to run on thread
		for(int i=0;i<=10;i++){
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
}