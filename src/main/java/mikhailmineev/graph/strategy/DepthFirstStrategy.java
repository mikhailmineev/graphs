package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Branch;
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
    public Route findRoute(Node from, Predicate<Node> found, StatisticsWriter statistics) {
        LinkedList<Pair<Node, Integer>> toVisit = new LinkedList<>();
        toVisit.add(new Pair<>(from, 0));

        Set<Node> visited = new HashSet<>();

        Map<Node, Branch> routes = new HashMap<>();
        routes.put(from, null);

        Pair<Node, Integer> current;
        while ((current = toVisit.pop()) != null) {
            Node node = current.left();

            if (found.test(node)) {
                Route route = NodeScanner.buildRoute(from, node, routes, newRouteSupplier);
                statistics.found(route);
                return route;
            }

            NodeScanner.scanNode(routes, current, (b, s) -> toVisit.push(new Pair<>(b.to(), s)), visited);

            visited.add(node);
            statistics.visited(node);
        }

        throw new RuntimeException("Failed to find route");
    }

}
