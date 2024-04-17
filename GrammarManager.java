import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class GrammarManager {
    private Map<String, Grammar> grammars;
    private int grammarCount;

    public GrammarManager() {
        grammars = new HashMap<>();
        grammarCount = 0;
    }

    public void openGrammar(String id, Grammar grammar) {
        grammars.put(id, grammar);
    }

    public void listGrammars() {
        System.out.println("List of grammars:");
        for (String id : grammars.keySet()) {
            System.out.println(id);
        }
    }

    public void printGrammar(String id) {
        Grammar grammar = grammars.get(id);
        if (grammar != null) {
            System.out.println("Grammar: " + grammar.getId());
            for (Map.Entry<Integer, Rule> entry : grammar.getRules().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().getRule());
            }
        } else {
            System.out.println("Grammar not found.");
        }
    }

    public void saveGrammar(String id, String filename) {
        Grammar grammar = grammars.get(id);
        if (grammar != null) {
            try (FileWriter writer = new FileWriter(filename)) {
                for (Map.Entry<Integer, Rule> entry : grammar.getRules().entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue().getRule() + "\n");
                }
                System.out.println("Grammar saved to file: " + filename);
            } catch (IOException e) {
                System.out.println("Error saving grammar to file: " + e.getMessage());
            }
        } else {
            System.out.println("Grammar not found.");
        }
    }

    public void addRule(String id, Rule rule) {
        Grammar grammar = grammars.get(id);
        if (grammar != null) {
            int ruleNumber = grammar.getRules().size() + 1;
            grammar.addRule(ruleNumber, rule);
            System.out.println("Rule added to grammar " + id);
        } else {
            System.out.println("Grammar not found.");
        }
    }

    public void removeRule(String id, int n) {
        Grammar grammar = grammars.get(id);
        if (grammar != null) {
            grammar.removeRule(n);
            System.out.println("Rule " + n + " removed from grammar " + id);
        } else {
            System.out.println("Grammar not found.");
        }
    }

    public void union(String id1, String id2) {
        Grammar grammar1 = grammars.get(id1);
        Grammar grammar2 = grammars.get(id2);
        if (grammar1 != null && grammar2 != null) {
            Grammar newGrammar = new Grammar("Union_" + id1 + "_" + id2);
            for (Map.Entry<Integer, Rule> entry : grammar1.getRules().entrySet()) {
                newGrammar.addRule(entry.getKey(), entry.getValue());
            }
            int ruleNumberOffset = grammar1.getRules().size();
            for (Map.Entry<Integer, Rule> entry : grammar2.getRules().entrySet()) {
                newGrammar.addRule(entry.getKey() + ruleNumberOffset, entry.getValue());
            }
            openGrammar(newGrammar.getId(), newGrammar);
            System.out.println("Union of grammars " + id1 + " and " + id2 + " created with id " + newGrammar.getId());
        } else {
            System.out.println("One or both grammars not found.");
        }
    }
}
//second commit