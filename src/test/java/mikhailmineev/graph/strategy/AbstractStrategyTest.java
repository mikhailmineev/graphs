package mikhailmineev.graph.strategy;

import mikhailmineev.graph.solution.Route;
import mikhailmineev.graph.stats.DefaultStatistics;

import mikhailmineev.graph.stats.Statistics;
import mikhailmineev.graph.stats.StatisticsReader;
import mikhailmineev.graph.store.*;
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
        var graph = new RelationalGraph(SampleGraphs.threeNodeGraph()).buildGraph();

        var route = strategy.findRoute("a", "c", graph, statistics);

        verifyTest2NodePath(route, statistics);
    }

    protected abstract void verifyTest2NodePath(Route route, StatisticsReader statistics);

    @Test
    public void test4NodePath() {
        var graph = new RelationalGraph(SampleGraphs.fourNodeDiamondGraph()).buildGraph();

        var route = strategy.findRoute("a", "d", graph, statistics);

        verifyTest4NodePath(route, statistics);
    }

    protected abstract void verifyTest4NodePath(Route route, StatisticsReader statistics);

    @Test
    public void test3NodeTree() {
        var graph = new RelationalGraph(SampleTrees.threeNodeTree()).buildGraph();

        var route = strategy.findRoute("a", "c", graph, statistics);

        verifyTest3NodeTree(route, statistics);
    }

    protected abstract void verifyTest3NodeTree(Route route, StatisticsReader statistics);

    @Test
    public void test5NodeTree() {
        var graph = new RelationalGraph(SampleTrees.fiveNodeTree()).buildGraph();

        var route = strategy.findRoute("a", "e", graph, statistics);

        verifyTest5NodeTree(route, statistics);
    }

    protected abstract void verifyTest5NodeTree(Route route, StatisticsReader statistics);

    @Test
    public void test5NodeWeightedTree() {
        var graph = new RelationalGraph(SampleGraphs.fiveNodeWeightedGraph()).buildGraph();

        var route = strategy.findRoute("a", "e", graph, statistics);

        verifyTest5NodeWeightedTree(route, statistics);
    }

    protected abstract void verifyTest5NodeWeightedTree(Route route, StatisticsReader statistics);

}
