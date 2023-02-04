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
        TreeSet<Pair<Node, Integer>> toVisit = new TreeSet<>(LOWEST_SCORE);
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

            for (Branch branch : node.getBranches()) {
                var nextNode = branch.to();

                if (visited.contains(nextNode)) {
                    continue;
                }

                Pair<Node, Integer> entry = toVisit.stream().filter(e -> e.left().equals(nextNode)).findFirst()
                        .orElseGet(() -> new Pair<>(nextNode, Integer.MAX_VALUE));
                assignNewScore(current.right(), statistics, toVisit, routes, branch, entry);
            }

            visited.add(node);
            statistics.visited(node);
        }

        throw new RuntimeException("Failed to find route");
    }

    private void assignNewScore(int parentScore, StatisticsWriter statistics,
                                       Set<Pair<Node, Integer>> toVisit, Map<Node, Branch> routes,
                                       Branch branch, Pair<Node, Integer> entry) {
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
