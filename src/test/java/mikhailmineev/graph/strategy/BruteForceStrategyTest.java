package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Route;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BruteForceStrategyTest extends AbstractStrategyTest {

    public BruteForceStrategyTest() {
        super(BruteforceStrategy::new);
    }

    @Override
    protected void verifyTest2NodePath(Route route) {
        assertEquals(Arrays.asList("a", "c"), route.getNodeNames());
    }

    @Override
    protected void verifyTest4NodePath(Route route) {
        assertEquals(Arrays.asList("a", "d", "c"), route.getNodeNames());

    }

    @Override
    protected void verifyTest3NodeTree(Route route) {
        assertEquals(Arrays.asList("a", "c"), route.getNodeNames());
    }

    @Override
    protected void verifyTest5NodeTree(Route route) {
        assertEquals(Arrays.asList("a", "c", "e"), route.getNodeNames());
    }
}
