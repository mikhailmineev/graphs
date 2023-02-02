package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class DefaultStatistics implements Statistics {

    private final Collection<Node> nodesVisited = new LinkedList<>();

    @Override
    public void visited(Node node) {
        System.out.println("Visited " + node);
        nodesVisited.add(node);
    }

    @Override
    public Collection<Node> getNodesVisited() {
        return nodesVisited;
    }

    @Override
    public Collection<String> getNodesNamesVisited() {
        return getNodesVisited()
                .stream()
                .sorted(Comparator.comparing(Node::getName))
                .map(Node::getName)
                .collect(Collectors.toList());
    }
}
