package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DefaultStatistics implements Statistics {

    private Collection<Node> nodesVisited;

    @Override
    public void setNodesVisited(Collection<Node> nodesVisited) {
        this.nodesVisited = nodesVisited;
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
