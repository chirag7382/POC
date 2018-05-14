import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ThreadProcessor implements Runnable{
	private int id;
	public ThreadProcessor(int id){
		this.id=id;
	}
	@Override
	public void run() {
		System.out.println("Starting Process ID "+ id);
		try {
			// Some usefull task that takes time 
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ending Process ID "+ id);
	}
	
}

public class Thread5 {

    public static void main(String[] args) throws InterruptedException {
       ExecutorService exec=Executors.newFixedThreadPool(1);
       for(int i=0;i<10;i++){
    	   exec.submit(new ThreadProcessor(i));
       }
       long start=System.currentTimeMillis();
       System.out.println("All Tasks Submitted...");
       exec.shutdown();
       
       exec.awaitTermination(1, TimeUnit.DAYS);
       
       long end=System.currentTimeMillis();
       
       System.out.println("All Tasks Completed in time.."+(end-start)+" ms");
    }
}