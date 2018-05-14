import java.util.Scanner;

// Use of volatile
public class Thread2 {

	public static void main(String[] args) {
		Process p1=new Process();
		p1.start();
		Scanner s=new Scanner(System.in);
		s.nextLine();
		System.out.println("Hit return to stop ..");
		
		p1.shutDown();
		
	}
	
}
class Process extends Thread{
	/*
	 *  volatile keyword is used to allow other threads to modify member variables of a different thread
	 *  it prevents java from caching the member data.
	 */
	private volatile boolean isRunning=true;
	@Override
	public void run() {
		while(isRunning){
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void shutDown(){
		this.isRunning=false;
	}
	public void startUp(){
		this.isRunning=true;
	}

}
