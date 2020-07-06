import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SummingSingleThreadToolPartTwo extends RecursiveTask<Integer> {
    private List<Integer> digits;
    private int numberOfThreads;

    public SummingSingleThreadToolPartTwo(List<Integer> digits, int numberOfThreads) {
        this.digits = digits;
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    protected Integer compute() {
        int threshold = digits.size() / numberOfThreads;
        int firstResult = sum(digits.subList(0, threshold));
        int nextResult = 0;
        if (digits.size() > threshold) {
            nextResult = new SummingSingleThreadToolPartTwo(
                    digits.subList(threshold, digits.size()),
                    numberOfThreads - 1).compute();
        }
        return firstResult + nextResult;
    }

    private Integer sum(List<Integer> digits) {
        return digits.stream().reduce(0, Integer::sum);
    }
}
