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
     * Adds branch to the tail of the solution and returns same or new tail.
     *
     * @param branchToNode branch that should lead to new end of route
     * @return route with added tail
     */
    default Route addBranch(Branch branchToNode) {
        return addNode(branchToNode.to(), branchToNode);
    }

    /**
     * Adds node to the tail of the solution and returns same or new tail.
     *
     * @param node visited node
     * @param branchToNode branch that leads to "node" in the first argument
     * @return route with added tail
     */
    Route addNode(Node node, Branch branchToNode);

    /**
     * Creates new immutable route using default implementation.
     *
     * @param firstNode node, the route begins with
     * @return new route
     */
    static Route newRoute(Node firstNode) {
        return new NodeBasedRoute(firstNode);
    }

}
