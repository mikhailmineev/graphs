package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;
import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.StatisticsWriter;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class DijkstraStrategy implements Strategy {

    private static final Comparator<Pair<Node, Integer>> LOWEST_SCORE = Comparator
            .comparingInt((Pair<Node, Integer> e) -> e.right())
            .thenComparing((Pair<Node, Integer> e) -> e.left().getName());

    private final Function<Node, Route> newRouteSupplier;

    public DijkstraStrategy(Function<Node, Route> newRouteSupplier) {
        this.newRouteSupplier = newRouteSupplier;
    }

    @Override
    public Route findRoute(Node from, Predicate<Node> found, StatisticsWriter statistics) {
        Pollable<Pair<Node, Integer>> toVisit = Pollable.of(new TreeSet<>(LOWEST_SCORE));
        toVisit.add(new Pair<>(from, 0));

        Map<Node, Branch> routes = new HashMap<>();
        routes.put(from, null);

        return NodeScanner.scanAllNodes(from, toVisit, found, routes, newRouteSupplier, statistics,
                        (b, s) -> assignNewScore(s, statistics, toVisit, routes, b))
                .orElseThrow(() -> new RuntimeException("Failed to find route"));
    }

    private void assignNewScore(int parentScore, StatisticsWriter statistics, Pollable<Pair<Node, Integer>> toVisit,
                                Map<Node, Branch> routes, Branch branch) {
        Pair<Node, Integer> entry = toVisit
                .stream()
                .filter(e -> e.left().equals(branch.to()))
                .findFirst()
                .orElseGet(() -> new Pair<>(branch.to(), Integer.MAX_VALUE));

        int newScore = parentScore + branch.length();
        if (entry.right() <= newScore) {
            return;
        }
        toVisit.remove(entry);
        toVisit.add(new Pair<>(entry.left(), newScore));
        statistics.score(entry.left(), newScore);
        routes.put(entry.left(), branch);
    }

}
