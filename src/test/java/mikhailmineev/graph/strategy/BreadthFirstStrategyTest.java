package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Route;
import mikhailmineev.graph.stats.Statistics;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreadthFirstStrategyTest extends AbstractStrategyTest {

    public BreadthFirstStrategyTest() {
        super(BreadthFirstStrategy::new);
    }

    @Override
    protected void verifyTest2NodePath(Route route, Statistics statistics) {
        assertEquals(List.of("a", "c"), route.getNodeNames());
        assertEquals(List.of("a", "b"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest4NodePath(Route route, Statistics statistics) {
        assertEquals(List.of("a", "b", "d"), route.getNodeNames());
        assertEquals(List.of("a", "b", "c"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest3NodeTree(Route route, Statistics statistics) {
        assertEquals(List.of("a", "c"), route.getNodeNames());
        assertEquals(List.of("a", "b"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest5NodeTree(Route route, Statistics statistics) {
        assertEquals(List.of("a", "c", "e"), route.getNodeNames());
        assertEquals(List.of("a", "b", "c", "d"), statistics.getNodesNamesVisited());
    }
}
