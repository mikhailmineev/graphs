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
    public void test3NodeGraph() {
        var graph = new RelationalGraph(SampleGraphs.threeNodeGraph()).buildGraph();

        var route = strategy.findRoute("a", n -> n.getName().equals("c"), graph, statistics);

        verify3NodeGraph(route, statistics);
    }

    protected abstract void verify3NodeGraph(Route route, StatisticsReader statistics);

    @Test
    public void test4NodeDiamondGraph() {
        var graph = new RelationalGraph(SampleGraphs.fourNodeDiamondGraph()).buildGraph();

        var route = strategy.findRoute("a", n -> n.getName().equals("d"), graph, statistics);

        verify4NodeDiamondGraph(route, statistics);
    }

    protected abstract void verify4NodeDiamondGraph(Route route, StatisticsReader statistics);

    @Test
    public void test3NodeTree() {
        var graph = new RelationalGraph(SampleTrees.threeNodeTree()).buildGraph();

        var route = strategy.findRoute("a", n -> n.getName().equals("c"), graph, statistics);

        verify3NodeTree(route, statistics);
    }

    protected abstract void verify3NodeTree(Route route, StatisticsReader statistics);

    @Test
    public void test5NodeTree() {
        var graph = new RelationalGraph(SampleTrees.fiveNodeTree()).buildGraph();

        var route = strategy.findRoute("a", n -> n.getName().equals("e"), graph, statistics);

        verify5NodeTree(route, statistics);
    }

    protected abstract void verify5NodeTree(Route route, StatisticsReader statistics);

    @Test
    public void test5NodeWeightedGraph() {
        var graph = new RelationalGraph(SampleGraphs.fiveNodeWeightedGraph()).buildGraph();

        var route = strategy.findRoute("a", n -> n.getName().equals("e"), graph, statistics);

        verify5NodeWeightedGraph(route, statistics);
    }

    protected abstract void verify5NodeWeightedGraph(Route route, StatisticsReader statistics);


    @Test
    public void testGermanCitiesGraph() {
        var graph = new RelationalGraph(SampleGraphs.germanCitiesGraph()).buildGraph();

        var route = strategy.findRoute("Frankfurt", n -> n.getName().equals("Munchen"), graph, statistics);

        verifyGermanCitiesGraph(route, statistics);
    }

    protected abstract void verifyGermanCitiesGraph(Route route, StatisticsReader statistics);

}
