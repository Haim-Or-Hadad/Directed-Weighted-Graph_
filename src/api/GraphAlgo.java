package api;

import java.util.List;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms{
    DirectedWeightedGraph graph;


    @Override
    public void init(DirectedWeightedGraph g) {
        graph=g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        if(graph.nodeSize()!=0){
            DirectedWeightedGraph graphCopy=new Graph();
            for (int i=0;i<=graph.nodeSize();i++){
                graphCopy.addNode(new Node(i, graph.getNode(i).getLocation()));
                for (int j=0;j<=graph.nodeSize();j++){
                    if(graph.getEdge(i,j)!=null){
                        EdgeData edge=new Edge(i,j,graph.getEdge(i,j).getWeight());
                        graphCopy.connect(edge.getSrc(),edge.getDest(),edge.getWeight());
                    }
                }
            }
            return graphCopy;
        }
        return null;
    }

    @Override
    public boolean isConnected() {

        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
