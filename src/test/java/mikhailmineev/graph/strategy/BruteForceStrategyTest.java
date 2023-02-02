package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Route;
import mikhailmineev.graph.stats.Statistics;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BruteForceStrategyTest extends AbstractStrategyTest {

    public BruteForceStrategyTest() {
        super(BruteforceStrategy::new);
    }

    @Override
    protected void verifyTest2NodePath(Route route, Statistics statistics) {
        assertEquals(List.of("a", "c"), route.getNodeNames());
        assertEquals(List.of("a"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest4NodePath(Route route, Statistics statistics) {
        assertEquals(List.of("a", "c", "d"), route.getNodeNames());
        assertEquals(List.of("a", "c"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest3NodeTree(Route route, Statistics statistics) {
        assertEquals(List.of("a", "c"), route.getNodeNames());
        assertEquals(List.of("a"), statistics.getNodesNamesVisited());
    }

    @Override
    protected void verifyTest5NodeTree(Route route, Statistics statistics) {
        assertEquals(List.of("a", "c", "e"), route.getNodeNames());
        assertEquals(List.of("a", "c", "d"), statistics.getNodesNamesVisited());
    }
}
