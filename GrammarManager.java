import java.util.HashMap;
import java.util.Map;

class GrammarManager {
    private Map<String, Grammar> grammars;
    private int grammarCount;

    public GrammarManager() {
        grammars = new HashMap<>();
        grammarCount = 0;
    }
}
