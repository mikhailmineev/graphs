package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3 Node tree with structure
 * <pre>
 *   a
 *  / \
 * b   c
 * </pre>
 */
public class ThreeNodeTree implements GraphStore {

    @Override
    public Map<String, Node> returnGraph() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");

        Branch ab = new Branch(a, b, 3);
        Branch ba = new Branch(b, a, 3);
        Branch ac = new Branch(a, c, 3);
        Branch ca = new Branch(c, a, 3);

        a.setBranches(Arrays.asList(ab, ac));
        b.setBranches(List.of(ba));
        c.setBranches(List.of(ca));

        return new HashMap<>() {{
            put(a.getName(), a);
            put(b.getName(), b);
            put(c.getName(), c);
        }};
    }
}
