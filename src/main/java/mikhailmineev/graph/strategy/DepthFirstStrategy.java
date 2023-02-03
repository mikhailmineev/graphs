package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;
import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.StatisticsWriter;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class DepthFirstStrategy implements Strategy {

    private final Function<Node, Route> newRouteSupplier;

    public DepthFirstStrategy(Function<Node, Route> newRouteSupplier) {
        this.newRouteSupplier = newRouteSupplier;
    }

    @Override
    public Route findRoute(String from, Predicate<Node> found, Map<String, Node> graph, StatisticsWriter statistics) {
        Node start = Validations.getNode(from, graph);

        Set<Node> visited = new HashSet<>();

        LinkedList<Pair<Node, Route>> toVisit = new LinkedList<>();
        Route route = newRouteSupplier.apply(start);

        toVisit.add(new Pair<>(start, route));

        Pair<Node, Route> current;
        while ((current = toVisit.pop()) != null) {
            Node node = current.left();

            if (found.test(node)) {
                statistics.found(current.right());
                return current.right();
            }

            if (visited.contains(node)) {
                continue;
            }

            NodeScanner.scanNode(current, toVisit::push, visited);

            visited.add(node);
            statistics.visited(node);
        }

        throw new RuntimeException("Failed to find route");
    }

}
