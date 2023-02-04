package mikhailmineev.graph.core;

import java.util.List;

/**
 * Basic interface for Nodes.
 * Implementations may be immutable or lazy.
 * <p>
 * Node implementations must follow the contract:
 * <ul>
 *     <li>Nodes representing the same thing must refer the same object ({@code a == b} has value {@code true})</li>
 *     <li>Nodes representing different things must have different names ({@code a.getName().equals(b.getName())}
 *     has value {@code false})</li>
 *     <li>Repetitive calls of {@code Node.getBranches()} must return the same result</li>
 * </ul>
 */
public interface Node {

    /**
     * Get branch list.
     * Implementations may have lazy branch list.
     *
     * @return branches with child nodes inside
     */
    List<Branch> getBranches();

    /**
     * Get node name.
     *
     * @return node name
     */
    String getName();
}
