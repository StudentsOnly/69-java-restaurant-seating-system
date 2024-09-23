import java.util.concurrent.Semaphore;

public class Restaurant {
  Semaphore semaphore = new Semaphore(4);

  public boolean sit() {

    boolean satDown = false;

    try {
      semaphore.acquire();
      synchronized (this) { // emulate access to chair resource
        System.out.println(Thread.currentThread().getName() + " Guest sits down.");
        satDown = true;
      }
        Thread.sleep(1000);
      synchronized (this) { // freeing up chair resource
        System.out.println(Thread.currentThread().getName() + " Guest leaves.");
      }
    } catch (InterruptedException e) {
        e.printStackTrace();
    } finally {
      semaphore.release();
    }

    return satDown;
  }
}
