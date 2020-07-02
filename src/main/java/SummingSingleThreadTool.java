import java.util.concurrent.Callable;
import java.util.List;

public class SummingSingleThreadTool implements Callable {
    private List<Integer> digits;

    public SummingSingleThreadTool(List<Integer> digits) {
        this.digits = digits;
    }

    public Integer call() throws Exception {
        return digits.stream().mapToInt(Integer::intValue).sum();
    }
}
