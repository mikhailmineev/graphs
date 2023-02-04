package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;
import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.StatisticsWriter;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class BreadthFirstStrategy implements Strategy {

    private static final Comparator<Pair<Node, Integer>> DEPTH_FIRST = Comparator
            .comparingInt((Pair<Node, Integer> e) -> e.right())
            .thenComparing((Pair<Node, Integer> e) -> e.left().getName());

    private final Function<Node, Route> newRouteSupplier;

    public BreadthFirstStrategy(Function<Node, Route> newRouteSupplier) {
        this.newRouteSupplier = newRouteSupplier;
    }

    @Override
    public Route findRoute(Node from, Predicate<Node> found, StatisticsWriter statistics) {
        TreeSet<Pair<Node, Integer>> toVisit = new TreeSet<>(DEPTH_FIRST);
        toVisit.add(new Pair<>(from, 0));

        Set<Node> visited = new HashSet<>();

        Map<Node, Branch> routes = new HashMap<>();
        routes.put(from, null);

        Pair<Node, Integer> current;
        while ((current = toVisit.pollFirst()) != null) {
            Node node = current.left();

            if (found.test(node)) {
                Route route = NodeScanner.buildRoute(from, node, routes, newRouteSupplier);
                statistics.found(route);
                return route;
            }

            NodeScanner.scanNode(routes, current, (b, s) -> toVisit.add(new Pair<>(b.to(), s + 1)), visited);

            visited.add(node);
            statistics.visited(node);
        }

        throw new RuntimeException("Failed to find route");
    }

}
