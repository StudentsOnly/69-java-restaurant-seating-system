import java.util.concurrent.*;

class Customer implements Runnable {
    private final int customerId;
    private final Semaphore seats;

    public Customer(int customerId, Semaphore seats) {
        this.customerId = customerId;
        this.seats = seats;
    }

    @Override
    public void run() {
        try {
            System.out.println("Customer " + customerId + " is trying to get a seat.");

            // Acquire a seat
            seats.acquire();
            System.out.println("Customer " + customerId + " got a seat.");

              Thread.sleep( 3000);  //  wait for eating
            System.out.println("Customer " + customerId + " has finished eating and left.");

            seats.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}