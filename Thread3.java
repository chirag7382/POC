
public class Thread3 {
	
	private static int count=0;
	// Synchronized tells the threads to wait till the current invocation of the method has ended gracefully.
	// It locks the thread till this happens. So it prevents race condition between two threads.!
	public synchronized static void incrementCount(){
		count++;
	}
	public static void main(String[] args){
		runMultipleThreads();
	}
	
	public static void runMultipleThreads(){
		Thread t1=new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=1;i<=10000;i++){
					incrementCount();
				}
			}
			
		});
		Thread t2=new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=1;i<=10000;i++){
					incrementCount();
				}
			}
			
		});
		t1.start();
		t2.start();
		
		// Thread.join tells the invoking thread (here default JVM thread on which main is being executed
		// , to wait till the inner thread has completed the execution
		
		// if we dont put join, current thread will invoke t1.start and t2.start and then print sysout and then die.
		// This might not allow the threads t1 and t2 to complete its tasks.
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("count is "+count);
	}
}
