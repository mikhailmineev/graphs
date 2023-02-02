package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 5 Node tree with structure and different weights
 * <pre>
 *      a
 * /(2) |(1) \(2)
 * b    c     d
 * \(2) |(1) /(2)
 *      e
 * </pre>
 */
public class FourNodeWeightedTree implements GraphStore {

    @Override
    public Map<String, Node> returnGraph() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");

        Branch ab = new Branch(a, b, 2);
        Branch ba = new Branch(b, a, 2);
        Branch ac = new Branch(a, c, 1);
        Branch ca = new Branch(c, a, 1);
        Branch ad = new Branch(a, d, 2);
        Branch da = new Branch(d, a, 2);
        Branch be = new Branch(b, e, 2);
        Branch eb = new Branch(e, b, 2);
        Branch ce = new Branch(c, e, 1);
        Branch ec = new Branch(e, c, 1);
        Branch de = new Branch(d, e, 2);
        Branch ed = new Branch(e, d, 2);

        a.setBranches(Arrays.asList(ab, ac, ad));
        b.setBranches(Arrays.asList(ba, be));
        c.setBranches(Arrays.asList(ca, ce));
        d.setBranches(Arrays.asList(da, de));
        e.setBranches(Arrays.asList(eb, ec, ed));

        return new HashMap<>() {{
            put(a.getName(), a);
            put(b.getName(), b);
            put(c.getName(), c);
            put(d.getName(), d);
            put(e.getName(), e);
        }};
    }
}
