package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2 Node tree with structure
 * <pre>
 * a
 * |
 * b
 * </pre>
 */
public class TwoNodeTree implements GraphStore {

    @Override
    public Map<String, Node> returnGraph() {
        Node a = new Node("a");
        Node b = new Node("b");

        Branch ab = new Branch(a, b, 3);
        Branch ba = new Branch(b, a, 3);

        a.setBranches(List.of(ab));
        b.setBranches(List.of(ba));

        return new HashMap<>() {{
            put(a.getName(), a);
            put(b.getName(), b);
        }};
    }
}
