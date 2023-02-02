package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Node;
import mikhailmineev.graph.core.Branch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 4 Node graph with structure
 * <pre>
 *   a
 *  / \
 * b   c
 *  \ /
 *   d
 * </pre>
 */
public class FourNodeDiamondGraph implements GraphStore {

    @Override
    public Map<String, Node> returnGraph() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");

        Branch ab = new Branch(a, b, 3);
        Branch ba = new Branch(b, a, 3);
        Branch bc = new Branch(b, c, 3);
        Branch cb = new Branch(c, b, 3);
        Branch ad = new Branch(a, d, 3);
        Branch da = new Branch(d, a, 3);
        Branch dc = new Branch(d, c, 3);
        Branch cd = new Branch(c, d, 3);

        a.setBranches(Arrays.asList(ab, ad));
        b.setBranches(Arrays.asList(ba, bc));
        c.setBranches(Arrays.asList(cb, cd));
        d.setBranches(Arrays.asList(da, dc));

        return new HashMap<>() {{
            put(a.getName(), a);
            put(b.getName(), b);
            put(c.getName(), c);
        }};
    }
}
