package mikhailmineev.graph.solution;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;

import java.util.List;

public interface Route {

    List<String> getNodeNames();

    int depth();

    int length();

    Route addNode(Node node, Branch branchToNode);

    static Route newRoute(Node firstNode) {
        return new NodeBasedRoute(firstNode);
    }

}
