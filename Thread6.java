import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
// Callable and Future
class CallableThreadProcessor1 implements Callable<Integer>{
	private int id;
	public CallableThreadProcessor1(int id){
		this.id=id;
	}
	
	@Override
	public Integer call() throws Exception {
		System.out.println("Starting Process ID "+ id);
		try {
			// Some usefull task that takes time
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ending Process ID "+ id);
		return 3;
	}
	
}

public class Thread6 {

    public static void main(String[] args) throws InterruptedException {
       ExecutorService exec=Executors.newFixedThreadPool(5);
       Future<Integer> future = null;
	
       for(int i=0;i<10;i++){
    	   future=exec.submit(new CallableThreadProcessor1(i));
       }
       long start=System.currentTimeMillis();
       System.out.println("All Tasks Submitted...");
       exec.shutdown();
       
       exec.awaitTermination(1, TimeUnit.DAYS);
       try {
		System.out.println("Returned Value is "+future.get());
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       long end=System.currentTimeMillis();
       
       System.out.println("All Tasks Completed in time.."+(end-start)+" ms");
    }
}