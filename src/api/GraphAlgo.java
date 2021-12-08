package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
        DirectedWeightedGraph graph;
        private final Double INFINITY=Double.MAX_VALUE;
        private int lastsrc;

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
                return new Graph(this.graph);
        }

        /**
         * @return true if and only if (iff) there is a valid path from each node to each
         * other node.
         * NOTE: assume directional graph (all n*(n-1) ordered pairs).
         * The function using a BFS algorithm that check for each node in the graph if he reached all the other nodes.
         */
        @Override
        public boolean isConnected() {
                RestTags_Info();
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


        @Override
        public double shortestPathDist(int src, int dest) {
                return dijkstra(src, dest,1);
                        }


        @Override
        public List<NodeData> shortestPath(int src, int dest) {
                dijkstra(src,dest,0);
                List<NodeData> path = new ArrayList<>();
                if(graph.getNode(dest)!=null) {
                        NodeData currNode = graph.getNode(dest);
                        while (currNode.getKey() != src) {
                                if (currNode.getKey()==-1)
                                        return null;
                                path.add(currNode);
                                int prev = currNode.getTag();
                                currNode = graph.getNode(prev);
                        }
                        path.add(graph.getNode(src));
                        List<NodeData> newpath = new ArrayList<>();

                        for (int i = path.size() - 1; i >= 0; i--) {
                                newpath.add(path.get(i));
                        }

                        return newpath;
                }
                else return null;
        }

        @Override
        public NodeData center() {
                if (!isConnected())return null;
                double []counter=new double[graph.nodeSize()+1];
                Iterator<NodeData> nodes =graph.nodeIter();
                while(nodes.hasNext()) {
                        double max=Integer.MIN_VALUE;
                        NodeData x = nodes.next();
                            for(int i=0;i<graph.nodeSize();i++){
                                    if(graph.getNode(i)!=null) {
                                            double curr = shortestPathDist(x.getKey(), i);
                                            if (curr > max) {
                                                    max = curr;
                                            }
                                    }
                }
                        counter[x.getKey()] +=max;
                  }
                double min1=INFINITY;
                int select=0;
                for (int i=0;i<counter.length;i++){
                        if(counter[i]<min1&&counter[i]!=0) {
                                min1 = counter[i];
                                select=i;
                        }
                }
                return graph.getNode(select);
        }

        private void createpermute(List<NodeData> cities,int index,List<List<NodeData>> all_permutation){
                for (int i = index; i < cities.size(); i++) {
                        Collections.swap(cities, i, index);
                        createpermute(cities, index + 1, all_permutation);
                        Collections.swap(cities, index, i);
                        if (index == cities.size() - 1) {
                                all_permutation.add(new ArrayList<>(cities));
                        }
                }

        }
        @Override
        public List<NodeData> tsp(List<NodeData> cities) {
                List<List<NodeData>> all_permutation=new LinkedList<>();
                createpermute(cities,0,all_permutation);//insert all the permutation to the list
                double mindist=INFINITY; //min dist that indicate which permutation has the shortest distance
                int per_num=0; //the permutation number in the list
                for (int i=0;i<all_permutation.size();i++){
                        List<NodeData> curr_per=all_permutation.get(i); //select a permutation
                        double curr_per_dist=0;// dist for the curr permutation
                        for (int j=0;j<curr_per.size()-1;j++){
                                curr_per_dist+=shortestPathDist(curr_per.get(j).getKey(),curr_per.get(j+1).getKey()); //check for src to dist at this permutation
                        }
                        if (curr_per_dist<mindist){ //if this permutation distance is lower then mindist then she is the shortest permutation.
                                mindist=curr_per_dist;
                                per_num=i;
                        }
                }
                return all_permutation.get(per_num);
        }

        @Override
        public boolean save(String file) {
                try {
                        JsonWriter writer =new JsonWriter(new FileWriter(file+".json"));
                        writer.beginObject();
                        writer.name("Edges");
                        writer.beginArray();
                        Iterator<EdgeData>edges=this.graph.edgeIter();
                        while (edges.hasNext()) {
                                writer.beginObject();
                                EdgeData curr_edge = edges.next();
                                writer.name("src").value(curr_edge.getSrc());
                                writer.name("w").value(curr_edge.getWeight());
                                writer.name("dest").value(curr_edge.getDest());
                                writer.endObject();
                        }
                        writer.endArray();
                        writer.name("Nodes");
                        writer.beginArray();
                        Iterator<NodeData>nodes=this.graph.nodeIter();
                        while (nodes.hasNext()){
                                writer.beginObject();
                                NodeData curr_Node= nodes.next();
                                String pos=curr_Node.getLocation().x()+","+curr_Node.getLocation().y()+","+curr_Node.getLocation().z();
                                writer.name("pos").value(pos);
                                writer.name("id").value(curr_Node.getKey());
                                writer.endObject();
                        }
                        writer.endArray();
                        writer.endObject();
                        writer.close();
                }
               catch (IOException e) {
                        e.printStackTrace();
                        return false;
                }
                return true;
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


        private double dijkstra(int src , int dest,int type){
                if (graph.getNode(src)==null||graph.getNode(dest)==null)
                        throw new RuntimeException("one of the nodes dosent exist");
                if (src!=lastsrc){
                        lastsrc=src;
                        RestTags_Info();
                }
                Node srcNode=(Node) graph.getNode(src);
                if(srcNode.getfrom_min(dest)!=Double.MAX_VALUE&&type==1) {
                        return srcNode.getfrom_min(dest);
                }
                Queue<NodeData>nodesQ= new LinkedList<>();
                nodesQ.add(graph.getNode(src));
                while(!nodesQ.isEmpty()) {
                        Node curr_node=(Node)nodesQ.poll();
                        curr_node.min_update(curr_node.getKey(),curr_node.getWeight());
                        curr_node.setInfo("Black");
                        Iterator<EdgeData> edges=graph.edgeIter(curr_node.getKey());
                        while(edges.hasNext()) {
                                EdgeData curr_edge=this.graph.getEdge(curr_node.getKey(),edges.next().getDest());
                                NodeData curr_dest= graph.getNode(curr_edge.getDest());
                                if ((curr_edge.getWeight()+srcNode.getfrom_min(curr_node.getKey()) <= srcNode.getfrom_min(curr_dest.getKey())))
                                {

                                        srcNode.min_update(curr_dest.getKey(),curr_edge.getWeight()+srcNode.getfrom_min(curr_edge.getSrc()));
                                        curr_dest.setTag(curr_node.getKey());

                                }
                                if (Objects.equals(curr_dest.getInfo(), "White"))
                                        nodesQ.add(curr_dest);

                        }
                }
                if (Objects.equals(srcNode.getfrom_min(dest), INFINITY))
                        return -1;
                return srcNode.getfrom_min(dest);
        }
        private double ArraySum(double[] shortestPath){
                double sum=0;
                for (double v : shortestPath)
                        if (v != INFINITY)
                                sum += v;
                return sum;
        }
 }
