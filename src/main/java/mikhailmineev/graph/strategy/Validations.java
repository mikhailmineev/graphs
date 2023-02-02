package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;

import java.util.Map;

public final class Validations {

    private Validations() {
        // utility class
    }

    public static Node getNode(String nodeName, Map<String, Node> graph) {
        Node node = graph.get(nodeName);
        if (node == null) {
            throw new IllegalArgumentException("No node " + nodeName + "in graph " + graph);
        }
        return node;
    }
}
