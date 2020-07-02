import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SummingSingleThreadToolPartTwo extends RecursiveTask<Integer> {
    private List<Integer> digits;

    public SummingSingleThreadToolPartTwo(List<Integer> digits) {
        this.digits = digits;
    }

    @Override
    protected Integer compute() {
        return digits.stream().mapToInt(Integer::intValue).sum();
    }
}
