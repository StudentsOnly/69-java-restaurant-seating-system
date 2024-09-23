import java.util.Random;
import java.util.concurrent.Semaphore;

public class Restaurant {

    private final Semaphore semaphore;
    Random r = new Random();
    private int seatingGuests = 0;

    public Restaurant(int seats) {
        this.semaphore = new Semaphore(seats);
    }

    public void sit() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e1) {
            System.out.println(e1.getMessage());
        }

        try {
            sitGuest();
        } finally {
            semaphore.release();
        }
    }


    private void sitGuest() {
        synchronized (this) {

            System.out.println("New guest");
            seatingGuests++;
            System.out.println("Current guests: " + seatingGuests);
        }

        try {
            Thread.sleep(r.nextInt(2000, 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            System.out.println("Guest leaving");
            seatingGuests--;
            System.out.println("Current guests: " + seatingGuests);
        }
    }
}

