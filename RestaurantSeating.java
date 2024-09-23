import java.util.concurrent.*;

public class RestaurantSeating {
    private static final int NUMBER_OF_SEATS = 5;
    private static final int NUMBER_OF_CUSTOMERS = 10;

    public static void main(String[] args) {

        Semaphore seats = new Semaphore(NUMBER_OF_SEATS);

        try (ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_CUSTOMERS)) {

            for (int i = 1; i <= NUMBER_OF_CUSTOMERS; i++) {
                Customer customer = new Customer(i, seats);
                executor.submit(customer);
            }

            executor.shutdown();

            try {
               executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Restaurant is closing for the day.");
    }
}
