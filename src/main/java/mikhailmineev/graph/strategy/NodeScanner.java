package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Pair;
import mikhailmineev.graph.solution.Route;

import java.util.Set;
import java.util.function.Consumer;

public final class NodeScanner {

    private NodeScanner() {
        // utility class
    }

    public static void scanNode(Pair<Node, Route> current, Consumer<Pair<Node, Route>> newNodeRegisterer,
                                       Set<Node> visited) {
        Node node = current.left();
        Route routeToCurrent = current.right();

        for (Branch branch : node.getBranches()) {
            var newRoute = routeToCurrent.addBranch(branch);
            var nextNode = branch.to();
            if (!visited.contains(nextNode)) {
                newNodeRegisterer.accept(new Pair<>(nextNode, newRoute));
            }
        }
    }
}
