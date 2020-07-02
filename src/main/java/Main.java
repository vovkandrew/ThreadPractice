import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int SIZE = 1000000;
    private static final int NUMOFTHREADS =
            Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(NUMOFTHREADS);
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            digits.add(new Random().nextInt(100));
        }
        int start = 0;
        int step = SIZE / NUMOFTHREADS;
        int end = SIZE / NUMOFTHREADS;
        int totalSum = 0;
        for (int i = 0; i < NUMOFTHREADS; i++) {
            try {
                totalSum = totalSum
                        + (Integer) es.submit(
                                new SummingSingleThreadTool(digits.subList(start, end)))
                                .get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            start = start + step;
            end = end + step;
        }
        es.shutdown();
        System.out.println(totalSum);
        ForkJoinPool pool = new ForkJoinPool(NUMOFTHREADS);
        int totalSumFork =
                pool.invoke(new SummingSingleThreadToolPartTwo(digits));
        System.out.println(totalSumFork);
    }
}
