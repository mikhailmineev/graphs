package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;
import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.StatisticsWriter;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class NodeScanner {

    private NodeScanner() {
        // utility class
    }

    public static Optional<Route> scanAllNodes(Node from, Pollable<Pair<Node, Integer>> toVisit, Predicate<Node> found,
                                               Map<Node, Branch> routes, Function<Node, Route> newRouteSupplier,
                                               StatisticsWriter statistics,
                                               BiConsumer<Branch, Integer> nonVisitedNodeConsumer) {
        Set<Node> visited = new HashSet<>();

        Pair<Node, Integer> current;
        while ((current = toVisit.first()) != null) {
            Node node = current.left();

            if (found.test(node)) {
                Route route = NodeScanner.buildRoute(from, node, routes, newRouteSupplier);
                statistics.found(route);
                return Optional.of(route);
            }

            NodeScanner.scanNode(routes, current, nonVisitedNodeConsumer, visited);

            visited.add(node);
            statistics.visited(node);
        }
        return Optional.empty();
    }

    public static void scanNode(Map<Node, Branch> routes, Pair<Node, Integer> current,
                                BiConsumer<Branch, Integer> nonVisitedNodeConsumer, Set<Node> visited) {
        Node node = current.left();
        Integer score = current.right();

        for (Branch branch : node.getBranches()) {
            var nextNode = branch.to();
            if (!visited.contains(nextNode)) {
                routes.putIfAbsent(nextNode, branch);
                nonVisitedNodeConsumer.accept(branch, score);
            }
        }
    }

    /**
     * Utility function to create solution route based on waypoints map.
     *
     * @param start start node in route
     * @param end end node in route
     * @param waypoints a map of nodes and branches that lead to those nodes
     * @param newRouteSupplier new route supplier
     * @return complete route from start node to end node
     */
    public static Route buildRoute(Node start, Node end, Map<Node, Branch> waypoints,
                                   Function<Node, Route> newRouteSupplier) {
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
