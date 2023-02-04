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
        Pollable<Pair<Node, Integer>> toVisit = Pollable.of(new TreeSet<>(DEPTH_FIRST));
        toVisit.add(new Pair<>(from, 0));

        Map<Node, Branch> routes = new HashMap<>();
        routes.put(from, null);

        return NodeScanner.scanAllNodes(from, toVisit, found, routes, newRouteSupplier, statistics,
                        (b, s) -> toVisit.add(new Pair<>(b.to(), s + 1)))
                .orElseThrow(() -> new RuntimeException("Failed to find route"));
    }

}
