package mikhailmineev.graph.store;

import mikhailmineev.graph.core.Branch;
import mikhailmineev.graph.core.Node;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreTest {

    @Test
    public void testThreeNodeGraph() {
        var graph = new RelationalGraph(SampleGraphs.threeNodeGraph()).buildGraph();

        assertEquals(3, graph.size());
        assertBranch("a", List.of("b", "c"), graph);
        assertBranch("b", List.of("a", "c"), graph);
        assertBranch("c", List.of("a", "b"), graph);
    }

    @Test
    public void testFourNodeDiamondGraph() {
        var graph = new RelationalGraph(SampleGraphs.fourNodeDiamondGraph()).buildGraph();

        assertEquals(4, graph.size());
        assertBranch("a", List.of("b", "c"), graph);
        assertBranch("b", List.of("a", "d"), graph);
        assertBranch("c", List.of("a", "d"), graph);
        assertBranch("d", List.of("b", "c"), graph);
    }

    @Test
    public void testFiveNodeWeightedGraph() {
        var graph = new RelationalGraph(SampleGraphs.fiveNodeWeightedGraph()).buildGraph();

        assertEquals(5, graph.size());
        assertBranch("a", List.of("b", "c", "d"), List.of(2, 1, 2), graph);
        assertBranch("b", List.of("a", "e"), List.of(2, 2), graph);
        assertBranch("c", List.of("a", "e"), List.of(1, 1), graph);
        assertBranch("d", List.of("a", "e"), List.of(2, 2), graph);
        assertBranch("e", List.of("b", "c", "d"), List.of(2, 1, 2), graph);
    }

    @Test
    public void testTwoNodeTree() {
        var graph = new RelationalGraph(SampleTrees.twoNodeTree()).buildGraph();

        assertEquals(2, graph.size());
        assertBranch("a", List.of("b"), graph);
        assertBranch("b", List.of("a"), graph);
    }

    @Test
    public void testThreeNodeTree() {
        var graph = new RelationalGraph(SampleTrees.threeNodeTree()).buildGraph();

        assertEquals(3, graph.size());
        assertBranch("a", List.of("b", "c"), graph);
        assertBranch("b", List.of("a"), graph);
        assertBranch("c", List.of("a"), graph);
    }

    @Test
    public void testFiveNodeTree() {
        var graph = new RelationalGraph(SampleTrees.fiveNodeTree()).buildGraph();

        assertEquals(5, graph.size());
        assertBranch("a", List.of("b", "c", "d"), graph);
        assertBranch("b", List.of("a"), graph);
        assertBranch("c", List.of("a", "e"), graph);
        assertBranch("d", List.of("a"), graph);
        assertBranch("e", List.of("c"), graph);
    }

    private static void assertBranch(String node, List<String> expectedChildren, Map<String, Node> graph) {
        assertBranch(node, expectedChildren, null, graph);
    }

    private static void assertBranch(String node, List<String> expectedChildren, List<Integer> weights, Map<String, Node> graph) {
        var branches = graph.get(node).getBranches();
        for (int i = 0; i < branches.size(); i++) {
            var branch = branches.get(i);
            var weight = weights == null ? 3 : weights.get(i);
            assertConcreteBranch(node, expectedChildren, branch, weight);
        }
        assertEquals(expectedChildren.size(), graph.get(node).getBranches().size());
    }

    private static void assertConcreteBranch(String fromNodeName, List<String> expectedChildren, Branch branch, int weight) {
        var toNodeName = branch.to().getName();
        assertEquals(fromNodeName, branch.from().getName(),
                () -> "Node " + fromNodeName + " has wrong from point in " + branch);
        assertTrue(expectedChildren.contains(toNodeName),
                () -> "Node " + toNodeName + " is not expected, expecting " + expectedChildren);
        assertEquals(weight, branch.length());
    }
}
