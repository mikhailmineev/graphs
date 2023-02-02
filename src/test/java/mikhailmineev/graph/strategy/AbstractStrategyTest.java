package mikhailmineev.graph.strategy;

import mikhailmineev.graph.core.Route;
import mikhailmineev.graph.stats.DefaultStatistics;

import mikhailmineev.graph.stats.Statistics;
import mikhailmineev.graph.stats.StatisticsReader;
import mikhailmineev.graph.store.FiveNodeTree;
import mikhailmineev.graph.store.FourNodeDiamondGraph;
import mikhailmineev.graph.store.ThreeNodeGraph;
import mikhailmineev.graph.store.ThreeNodeTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

public abstract class AbstractStrategyTest {

    private final Supplier<Strategy> strategySupplier;

    private Strategy strategy;
    private Statistics statistics;

    public AbstractStrategyTest(Supplier<Strategy> strategySupplier) {
        this.strategySupplier = strategySupplier;
    }

    @BeforeEach
    public void setup() {
        strategy = strategySupplier.get();
        statistics = new DefaultStatistics();
    }

    @Test
    public void test2NodePath() {
        var store = new ThreeNodeGraph();
        var graph = store.returnGraph();

        var route = strategy.findRoute("a", "c", graph, statistics);

        verifyTest2NodePath(route, statistics);
    }

    protected abstract void verifyTest2NodePath(Route route, StatisticsReader statistics);

    @Test
    public void test4NodePath() {
        var store = new FourNodeDiamondGraph();
        var graph = store.returnGraph();

        var route = strategy.findRoute("a", "d", graph, statistics);

        verifyTest4NodePath(route, statistics);
    }

    protected abstract void verifyTest4NodePath(Route route, StatisticsReader statistics);

    @Test
    public void test3NodeTree() {
        var store = new ThreeNodeTree();
        var graph = store.returnGraph();

        var route = strategy.findRoute("a", "c", graph, statistics);

        verifyTest3NodeTree(route, statistics);
    }

    protected abstract void verifyTest3NodeTree(Route route, StatisticsReader statistics);

    @Test
    public void test5NodeTree() {
        var store = new FiveNodeTree();
        var graph = store.returnGraph();

        var route = strategy.findRoute("a", "e", graph, statistics);

        verifyTest5NodeTree(route, statistics);
    }

    protected abstract void verifyTest5NodeTree(Route route, StatisticsReader statistics);

}
