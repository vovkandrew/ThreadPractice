import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int SIZE = 1000000;
    private static final int NUM_OF_THREADS =
            Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(NUM_OF_THREADS);
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            digits.add(new Random().nextInt(100));
        }
        int totalSum = calculateSum(es, digits);
        es.shutdown();
        ForkJoinPool pool = new ForkJoinPool(NUM_OF_THREADS);
        int totalSumFork =
                pool.invoke(new SummingSingleThreadToolPartTwo(digits, NUM_OF_THREADS));
        System.out.println(totalSum == totalSumFork);
    }

    private static Integer calculateSum(ExecutorService es, List<Integer> digits) {
        int start = 0;
        int step = SIZE / NUM_OF_THREADS;
        int end = SIZE / NUM_OF_THREADS;
        int totalSum = 0;
        for (int i = 0; i < NUM_OF_THREADS; i++) {
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
        return totalSum;
    }
}
