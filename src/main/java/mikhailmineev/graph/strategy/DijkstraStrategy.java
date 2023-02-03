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

    private final Function<Node, Route> newRouteSupplier;

    public DijkstraStrategy(Function<Node, Route> newRouteSupplier) {
        this.newRouteSupplier = newRouteSupplier;
    }

    @Override
    public Route findRoute(String from, Predicate<Node> found, Map<String, Node> graph, StatisticsWriter statistics) {
        Node start = Validations.getNode(from, graph);

        Comparator<Pair<Node, Integer>> lowestScore = Comparator
                .comparingInt((Pair<Node, Integer> e) -> e.right())
                .thenComparing((Pair<Node, Integer> e) -> e.left().getName());
        TreeSet<Pair<Node, Integer>> toVisit = new TreeSet<>(lowestScore);
        toVisit.add(new Pair<>(start, 0));

        List<Node> visited = new LinkedList<>();

        Map<Node, Branch> routes = new HashMap<>();
        routes.put(start,null);

        Pair<Node, Integer> current;
        while ((current = toVisit.pollFirst()) != null) {
            Node node = current.left();
            int score = current.right();

            if (found.test(node)) {
                Route route = buildRoute(start, node, routes);
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
                assignNewScore(score, statistics, toVisit, routes, branch, entry);
            }

            visited.add(node);
            statistics.visited(node);
        }

        throw new RuntimeException("Failed to find route");
    }

    private static void assignNewScore(int parentScore, StatisticsWriter statistics,
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

    private Route buildRoute(Node start, Node end, Map<Node, Branch> waypoints) {
        LinkedList<Branch> fromBeginning = new LinkedList<>();

        Branch branch;
        while ((branch = waypoints.get(end)) != null) {
            fromBeginning.push(branch);
            end = branch.from();
        }

        Route route = newRouteSupplier.apply(start);
        for (Branch step : fromBeginning) {
            route = route.addBranch(step);
        }
        return route;
    }

}
