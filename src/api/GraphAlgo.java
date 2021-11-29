package api;

import java.util.Iterator;
import java.util.List;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
        DirectedWeightedGraph graph;


        @Override
        public void init(DirectedWeightedGraph g) {
                graph = g;
        }

        @Override
        public DirectedWeightedGraph getGraph() {
                return graph;
        }

        @Override
        public DirectedWeightedGraph copy() {
                return new Graph(graph);
        }


        @Override
        public boolean isConnected() {
                int i=0;
                Iterator<NodeData> Nconnect=graph.nodeIter();
                Iterator<EdgeData> Econnect=graph.edgeIter(i);
                while(Nconnect.hasNext())
                {
                   DFS(graph, Nconnect.next().getKey());
                   while(Econnect.hasNext())
                   {
                       if (Econnect.next().getTag()==-1) {
                           return false;
                                }
                        }
                }
                return true;
        }

        private void DFS(DirectedWeightedGraph graph, int v) {
                graph.getNode(v).setTag(1);
                while(graph.edgeIter(v).hasNext()){
                        if(graph.edgeIter(v).next().getTag()==-1){
                                DFS(graph,v);
                        }
                }
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
