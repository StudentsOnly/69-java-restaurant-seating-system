import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Restaurant restaurant = new Restaurant(5);

        for (int i = 0; i < 20; i++) {
            service.submit(restaurant::sit);
        }

        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
