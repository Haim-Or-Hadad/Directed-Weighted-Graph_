package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
        DirectedWeightedGraph graph;

        public GraphAlgo(){
                this.graph=graph;
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
                return new Graph();
        }

        /**
         * @return true if and only if (iff) there is a valid path from each node to each
         * other node.
         * NOTE: assume directional graph (all n*(n-1) ordered pairs).
         * The function using a BFS algorithm that check for each node in the graph if he reached all the other nodes.
         */
        @Override
        public boolean isConnected() {
                Iterator<NodeData> Nodes=graph.nodeIter();
                while(Nodes.hasNext())
                {
                        int x=Nodes.next().getKey();
                   BFS(x);
                   Iterator<NodeData> Is_connected=graph.nodeIter();
                   while(Is_connected.hasNext())
                   {
                       if (Is_connected.next().getTag()==-1) {
                           return false;
                                }
                        }
                   RestTags();
                }
                return true;
        }

        /**
         *         This function rest the tags of the nodes in the graph for the next BFS usage.
         */
        private void RestTags(){
                Iterator<NodeData> Nodes=graph.nodeIter();
                while (Nodes.hasNext()){
                        Nodes.next().setTag(-1);
                }
        }

        /**
        This function is a BFS algorithm that check if the node sent to the function reach all the nodes in the graph.
        if the node reach a certain node it change is tag to 1.
        else the node tag will stay -1 which indicate the node hasn't been visited
         */
        private void BFS(int nodekey) {
                Queue<NodeData>ConnectedTo= new LinkedList<>();
                NodeData curr_node=this.graph.getNode(nodekey);
                ConnectedTo.add(curr_node);
                while (!ConnectedTo.isEmpty()){
                        curr_node=ConnectedTo.poll(); //take the Node in the top of the queue
                        if (curr_node.getTag()!=1) {  //check if you didnt vist him
                                curr_node.setTag(1); //if you didnt visit him set his tag to 1 (1=visit him)
                                Iterator<EdgeData> neighbors = this.graph.edgeIter(curr_node.getKey()); //go through his neighbors
                                while (neighbors.hasNext()) {
                                        NodeData temp_node=this.graph.getNode(neighbors.next().getDest());
                                        ConnectedTo.add(temp_node); //add the neighbors to the queue.
                                }
                        }
                }


        }
        private void runIterator(int src , Iterator nodes){
                int i=0;
                while(i>src){
                        nodes.hasNext();
                }
        }
        private void initialTomax(double []arr){
                for (int i=0 ; i<arr.length;i++){
                        arr[i]=70;
                }
        }

        @Override
        public double shortestPathDist(int src, int dest) {
                double[] shortestPath = new double[graph.nodeSize()];
                initialTomax(shortestPath);
                shortestPath[src] = 0;
                Iterator<NodeData> nodes=graph.nodeIter();
                runIterator(src,nodes);
                while(nodes.hasNext()) {
                        NodeData curr_node=this.graph.getNode(nodes.next().getKey());
                        double saveLastShortPath=(shortestPath[curr_node.getKey()]);
                        Iterator<EdgeData> edges=graph.edgeIter(curr_node.getKey());
                        while(edges.hasNext()) {
                                EdgeData curr_edge=this.graph.getEdge(curr_node.getKey(),edges.next().getDest());
                                if ((curr_edge.getWeight()+shortestPath[curr_node.getKey()] <= shortestPath[curr_edge.getDest()]))
                                {
                                        shortestPath[curr_edge.getDest()]=(curr_edge.getWeight())+saveLastShortPath;
                                }
                        }
                        }

                                return shortestPath[dest];
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
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(DirectedWeightedGraph.class, new JsonToGraph())
                        .create();
                try {
                        FileReader json = new FileReader(file);
                        this.graph= gson.fromJson(json, DirectedWeightedGraph.class);
                        return true;
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return false;
                }
        }
 }
