package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
        DirectedWeightedGraph graph;
        final Double INFINITY=Double.POSITIVE_INFINITY;

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
                        RestTags_Info();
                }
                return true;
        }

        /**
         *         This function rest the tags of the nodes in the graph for the next BFS usage.
         */
        private void RestTags_Info(){
                Iterator<NodeData> Nodes=graph.nodeIter();
                while (Nodes.hasNext()){
                        NodeData curr= Nodes.next();
                        curr.setTag(-1);
                        curr.setInfo("White");

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
                        nodes.next();
                        i++;
                }
        }
        private void initialTomax(double []arr){
                Arrays.fill(arr,INFINITY);

        }

        @Override
        public double shortestPathDist(int src, int dest) {
                double[] distArray=dijkstra(src, dest);
                return distArray[dest];
                        }


        @Override
        public List<NodeData> shortestPath(int src, int dest) {
                double s,r=shortestPathDist(src,dest);
                List<NodeData> path = new ArrayList<>();
                Iterator<NodeData> nodes=graph.nodeIter();
                while(nodes.hasNext()){
                        NodeData curr_node=this.graph.getNode(src);
                        Iterator<EdgeData> edges=graph.edgeIter(curr_node.getKey());
                        while(edges.hasNext()) {


                        }
                }
                return path;
        }

        @Override
        public NodeData center() {
                if (!isConnected())return null;
                double []sumPath=new double[graph.nodeSize()];
                int []counter=new int[graph.nodeSize()];
                Iterator<NodeData> nodes =graph.nodeIter();
                while(nodes.hasNext()) {
                        NodeData x = nodes.next();
                        Iterator<EdgeData> edges=graph.edgeIter(x.getKey());
                            for(int i=0;i<graph.nodeSize();i++){
                                if(shortestPathDist(x.getKey(), i)!=INFINITY)
                                sumPath[x.getKey()] += shortestPathDist(x.getKey(), i);
                                counter[x.getKey()] +=1;
                        //sumPath[x.getKey()] = ArraySum(dijkstra(x.getKey(), i));
                }
                  }
                double min=INFINITY;
                int select=0;
                for (int i=0;i<sumPath.length;i++){
                        if(sumPath[i]<min&&counter[i]==graph.nodeSize()) {
                                min = sumPath[i];
                                select=i;
                        }

                }
                return graph.getNode(select);
        }


        @Override
        public List<NodeData> tsp(List<NodeData> cities) {
                double [][] weight=matrixTsp();


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

        private double[] dijkstra(int src , int dest){
                        double[] shortestPath = new double[graph.nodeSize()+1];
                        initialTomax(shortestPath);
                        shortestPath[src] = 0;
                        Queue<NodeData>nodesQ= new LinkedList<>();
                        nodesQ.add(graph.getNode(src));
                        // runIterator(src,nodes);
                        while(!nodesQ.isEmpty()) {
                                NodeData curr_node=nodesQ.poll();
                                curr_node.setInfo("Black");
                                double saveLastShortPath=(shortestPath[curr_node.getKey()]);
                                Iterator<EdgeData> edges=graph.edgeIter(curr_node.getKey());
                                while(edges.hasNext()) {
                                        EdgeData curr_edge=this.graph.getEdge(curr_node.getKey(),edges.next().getDest());
                                        if ((curr_edge.getWeight()+shortestPath[curr_node.getKey()] <= shortestPath[curr_edge.getDest()]))
                                        {
                                                shortestPath[curr_edge.getDest()]=(curr_edge.getWeight())+saveLastShortPath;
                                        }
                                        NodeData temp=graph.getNode(curr_edge.getDest());
                                        if (temp.getInfo()=="White")
                                                nodesQ.add(temp);

                                }
                        }
                        RestTags_Info();
                        return shortestPath;
        }
        private double ArraySum(double[] shortestPath){
                double sum=0;
                for (double v : shortestPath)
                        if (v != INFINITY)
                                sum += v;
                return sum;
        }
        public double[][] matrixTsp(){
                int num=this.graph.nodeSize()-1;
                double[][] weight=new double[num][num];
                Iterator<NodeData> nodes=graph.nodeIter();
                Iterator<EdgeData> edges=graph.edgeIter(nodes.next().getKey());
                while(nodes.hasNext()){
                        while (edges.hasNext()){
                                        EdgeData curr=edges.next();
                                weight[nodes.next().getKey()][curr.getDest()]=curr.getWeight();
                        }


                }
                return weight;
        }

 }
