import java.util.List;
import java.util.concurrent.Callable;

public class SummingSingleThreadTool implements Callable {
    private List<Integer> digits;

    public SummingSingleThreadTool(List<Integer> digits) {
        this.digits = digits;
    }

    public Integer call() throws Exception {
        return digits.stream().mapToInt(Integer::intValue).sum();
    }
}
