package mikhailmineev.graph.solution;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;

import java.util.List;

public interface Route {

    /**
     * Provides path to solution represented with node names.
     *
     * @return route to solution
     */
    List<String> getNodeNames();

    /**
     * Provides number of nodes to solution.
     *
     * @return number of nodes to solution
     */
    int depth();

    /**
     * Provides length to solution.
     * Length is sum of weights in all branches.
     *
     * @return length to solution
     */
    int length();

    /**
     * Adds node to the tail of the solution and returns same or new tail.
     *
     * @return route with added tail
     */
    Route addNode(Node node, Branch branchToNode);

    /**
     * Creates new immutable route using default implementation.
     *
     * @return new route
     */
    static Route newRoute(Node firstNode) {
        return new NodeBasedRoute(firstNode);
    }

}
