import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {

  public static void main(String[] args) {

    ExecutorService executorService = Executors.newCachedThreadPool();
    Restaurant restaurant = new Restaurant();
    Callable<Boolean> task = restaurant::sit;

    List<Callable<Boolean>> tasks = Collections.nCopies(20, task);
    try {
      var futures = executorService.invokeAll(tasks);

      TimeUnit.SECONDS.sleep(10);
      executorService.shutdown();
      if(!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
        executorService.shutdownNow();
      }
      List<Boolean> peopleWhoSat = new ArrayList<>();
      for (var future : futures) {
        peopleWhoSat.add(future.get());
      }
      int numberSitting = Collections.frequency(peopleWhoSat, true);
      System.out.println("Number of people who sat down: " + numberSitting);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

  }


}
