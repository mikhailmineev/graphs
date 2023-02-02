package mikhailmineev.graph.stats;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.solution.Route;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class DefaultStatistics implements Statistics {

    private final Collection<Node> nodesVisited = new LinkedList<>();

    private Route finalRoute;

    @Override
    public void visited(Node node) {
        System.out.println("Visited " + node);
        nodesVisited.add(node);
    }

    @Override
    public void found(Route route) {
        System.out.println("Found route " + route);
        finalRoute = route;
    }

    @Override
    public Collection<Node> getNodesVisited() {
        return nodesVisited;
    }

    @Override
    public Collection<String> getNodesNamesVisited() {
        return getNodesVisited()
                .stream()
                .map(Node::getName)
                .collect(Collectors.toList());
    }
}
