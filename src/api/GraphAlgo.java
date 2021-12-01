package api;

import java.util.Iterator;
import java.util.List;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
        DirectedWeightedGraph graph;

        public GraphAlgo(){
                this.graph=null;
        }
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
                Iterator<NodeData> Nconnect=graph.nodeIter();
                Iterator<EdgeData> Econnect;
                while(Nconnect.hasNext())
                {
                        int x=Nconnect.next().getKey();
                        Econnect= graph.edgeIter(x);
                   BFS(graph, x);
                   while(Econnect.hasNext())
                   {
                       if (Econnect.next().getTag()==-1) {
                           return false;
                                }
                        }
                }
                return true;
        }

        private void BFS(DirectedWeightedGraph graph, int v) {
                while(graph.edgeIter(v).hasNext()){
                        if(graph.edgeIter(v).next().getTag()==-1){
                            int temp=graph.edgeIter(v).next().getDest();
                                graph.getEdge(v,temp).setTag(1);
                                DFS(graph,temp);
                        }
                        else continue;
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
