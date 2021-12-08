package Tests;

import api.*;
import org.junit.jupiter.api.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class GraphAlgo_Test {
    private final Double INFINITY=Double.MAX_VALUE;
    private static DirectedWeightedGraph graph = new Graph();
    private static DirectedWeightedGraphAlgorithms graphalgo = new GraphAlgo();

    @BeforeAll
    static void createConnected() {
        for (int i = 0; i <= 5; i++) {
            NodeData n = new Node(i,new geo_location(i, i, i));
            graph.addNode(n);
        }
        graph.connect(0, 2, 45);
        graph.connect(0, 1, 50);
        graph.connect(0, 3, 10);
        graph.connect(1, 2, 10);
        graph.connect(1, 3, 15);
        graph.connect(2, 4, 30);
        graph.connect(3, 0, 10);
        graph.connect(3, 4, 15);
        graph.connect(4, 1, 20);
        graph.connect(4, 2, 35);
        graph.connect(5, 4, 3);
        graphalgo.init(graph);
    }
    @Test
    @Order(1)
    void copyTest() {
        DirectedWeightedGraph copy = new Graph(graph);
        DirectedWeightedGraphAlgorithms gaCopy = new GraphAlgo();
        gaCopy.init(copy);
        assertEquals(graphalgo.getGraph().nodeSize(), gaCopy.getGraph().nodeSize());
        assertEquals(graphalgo.getGraph().edgeSize(), gaCopy.getGraph().edgeSize());
        copy.removeNode(0);
        gaCopy.init(copy);
        assertNotEquals(graphalgo.getGraph().nodeSize(), gaCopy.getGraph().nodeSize());
        assertNotEquals(graphalgo.getGraph().edgeSize(), gaCopy.getGraph().edgeSize());

    }

    @Test
    @Order(2)
    void isConnectedTest() {
        assertFalse(graphalgo.isConnected());
        DirectedWeightedGraph TestGraph = new Graph();
        DirectedWeightedGraphAlgorithms TestGraphAlgo = new GraphAlgo();
        TestGraphAlgo.init(TestGraph);
        assertTrue(TestGraphAlgo.isConnected());
        TestGraph.addNode(new Node(0,new geo_location(0, 0, 0)));
        TestGraphAlgo.init(TestGraph);
        assertTrue(TestGraphAlgo.isConnected());
        graph.connect(2, 5, 3);
        graphalgo.init(graph);
        graphalgo.isConnected();
        assertTrue(graphalgo.isConnected());

    }
    @Test
    @Order(3)
    void CenterTest(){
        graph.connect(2, 5, 3);
        assertEquals(1,graphalgo.center().getKey());
        graph.removeEdge(2, 5);
        graphalgo.init(graph);
        graphalgo.center();
        assertNotEquals(1,graphalgo.center());
    }

    @Test
    @Order(4)
    void shortestPathDistTest() {
        double dist = graphalgo.shortestPathDist(0, 3);
        assertEquals(10, dist);

        DirectedWeightedGraphAlgorithms TestGraphAlgo = new GraphAlgo();
        DirectedWeightedGraph TestGraph = graphalgo.copy();
        TestGraphAlgo.init(TestGraph);
        TestGraph.removeEdge(2,5);
        TestGraphAlgo.init(TestGraph);
        TestGraphAlgo.shortestPathDist(2, 5);
        dist = TestGraphAlgo.shortestPathDist(2, 5);
        assertEquals(INFINITY, dist);

        assertThrows(RuntimeException.class, () -> graphalgo.shortestPathDist(2, 10));
    }

    @Test
    @Order(5)
    void shortestPathTest() {
        List<NodeData> actual = graphalgo.shortestPath(0, 3);
        List<NodeData> expected = new LinkedList<>();
        expected.add(graph.getNode(0));
        expected.add(graph.getNode(3));
        assertEquals(expected, actual);

        actual = graphalgo.shortestPath(0, 4);
        expected.clear();
        expected.add(graph.getNode(0));
        expected.add(graph.getNode(3));
        expected.add(graph.getNode(4));
        assertEquals(expected, actual);

        actual = graphalgo.shortestPath(2, 2);
        expected.clear();
        expected.add(graph.getNode(2));
        assertEquals(expected, actual);

        assertThrows(RuntimeException.class, () -> graphalgo.shortestPath(2, 10));
    }


}