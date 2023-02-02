package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 5 Node tree with structure
 * <pre>
 *    a
 *  / | \
 * b  c  d
 *    |
 *    e
 * </pre>
 */
public class FiveNodeTree implements GraphStore {

    @Override
    public Map<String, Node> returnGraph() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");

        Branch ab = new Branch(a, b, 3);
        Branch ba = new Branch(b, a, 3);
        Branch ac = new Branch(a, c, 3);
        Branch ca = new Branch(c, a, 3);
        Branch ad = new Branch(a, d, 3);
        Branch da = new Branch(d, a, 3);
        Branch ce = new Branch(c, e, 3);
        Branch ec = new Branch(e, c, 3);

        a.setBranches(Arrays.asList(ab, ac, ad));
        b.setBranches(List.of(ba));
        c.setBranches(Arrays.asList(ca, ce));
        d.setBranches(List.of(da));
        e.setBranches(Arrays.asList(ca, ec));

        return new HashMap<>() {{
            put(a.getName(), a);
            put(b.getName(), b);
            put(c.getName(), c);
            put(d.getName(), d);
            put(e.getName(), e);
        }};
    }
}
