import java.util.LinkedHashMap;
import java.util.Map;


class Grammar {
    private String id;
    private Map<Integer, Rule> rules;

    public Grammar(String id) {
        this.id = id;
        this.rules = new LinkedHashMap<>(); // Запазваме реда на добавяне на правилата
    }

    public String getId() {
        return id;
    }

    public Map<Integer, Rule> getRules() {
        return rules;
    }

    public void addRule(int number, Rule rule) {
        rules.put(number, rule);
    }

    public void removeRule(int number) {
        rules.remove(number);
    }
}
