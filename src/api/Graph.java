package api;

import java.util.Iterator;
import java.util.HashMap;

public class Graph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes; //node hashmap each node id is the key for the hashmap
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges; //edge hashmap source node of the edge is the key
    // and the dest is the key the second hashmap which include the edge details.
    private int numOfNodes=0;
    private int numOfEdges=0;
    private int MC=0;

    /**
     * default constructor
     */
    public Graph(){
        this.nodes=new HashMap<>();
        this.edges=new HashMap<>();
        this.numOfNodes=0;
        this.numOfEdges=0;
        this.MC=0;
    }

    /**
     * Deep copy constructor
     * @param G-the graph that this function copy from.
     */
    public Graph(DirectedWeightedGraph G){
        this.nodes=new HashMap<>();
        this.edges=new HashMap<>();
        Iterator<NodeData>N=G.nodeIter();
        Iterator<EdgeData>E;
        while (N.hasNext()){
            NodeData curr=N.next();
            int CurrNodeKey=curr.getKey();
            E=G.edgeIter(CurrNodeKey);
            nodes.put(CurrNodeKey,curr);
            this.numOfNodes++;
            HashMap<Integer, EdgeData> temp= new HashMap<>();
            while (E.hasNext()){
                this.numOfEdges++;
                EdgeData tempE=E.next();
                temp.put(tempE.getDest(),tempE);
            }
            edges.put(CurrNodeKey,temp);
        }
    }


    /**
     * returns the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public NodeData getNode(int key) {
        if(nodes.containsKey(key))
        return nodes.get(key);
        return null;
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method run in O(1) time.
     * @param src -source node
     * @param dest- destination node
     * @return the new edge
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if (!edges.containsKey(src)|| !edges.get(src).containsKey(dest))
            return null;
        else return edges.get(src).get(dest);
    }

    /**
     * adds a new node to the graph with the given node_data.
     * Note: this method run in O(1) time.
     * @param n-the node we want to add to the graph.
     */
    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(),n);
        edges.put(n.getKey(),new HashMap<>());//create a hashmap for the edges going out from the node.
        this.numOfNodes++;
        this.MC++;
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method run in O(1) time.
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        EdgeData newEdge=new Edge(src,dest,w);
        edges.get(src).put(dest,newEdge);
        this.numOfEdges++;
        this.MC++;
    }

    /**
     * This method returns an Iterator for the nodes hashmap
     * collection representing all the nodes in the graph.
     * @return Iterator<node_data>
     */
    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();

    }

    /**
     * This method returns an Iterator for all the edges in this graph.
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        int i=0;
        Iterator<NodeData> nodeItr=this.nodeIter(); //Iterator that run over the nodes.
        HashMap<Integer,EdgeData> newE=new HashMap<>();
        Iterator<EdgeData>edge;  //Iterator that run over the edges of a specific node
        while (nodeItr.hasNext()){
            NodeData curr_node=nodeItr.next();
            edge=this.edgeIter(curr_node.getKey()); //Iterator get a node to run over
            while (edge.hasNext()) {
                newE.put(i,edge.next());
                i++;

            }
        }

        return newE.values().iterator();
    }

    /**
     * This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return edges.get(node_id).values().iterator();
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method run in O(k), V.degree=k, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key the key of the removed node.
     */
    @Override
    public NodeData removeNode(int key) {
        if (!this.nodes.containsKey(key))
            return null;
        else {
            Iterator<NodeData>Nodes=nodeIter();
            while (Nodes.hasNext()){
                NodeData curr= Nodes.next();
                if (edges.get(curr.getKey()).containsKey(key)){
                    edges.get(curr.getKey()).remove(key);
                    this.numOfEdges--;
                    this.MC++;


                }
            }
            this.numOfEdges-=edges.get(key).size();
            edges.remove(key);
            this.numOfNodes--;
            return this.nodes.remove(key);
        }

    }

    /**
     * Deletes the edge from the graph,
     * Note: this method run in O(1) time.
     * @param src-source node
     * @param dest-destination node
     * @return the data of the removed edge (null if none).
     */
    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(!this.edges.containsKey(src) || !this.edges.get(src).containsKey(dest))
            return null;
        EdgeData removedEdge=this.edges.get(src).remove(dest);
        Node srcnode=(Node)nodes.get(src);
        srcnode.remove_dist(dest);
        this.MC++;
        this.numOfEdges--;
        return removedEdge;
    }

    /** Returns the number of vertices (nodes) in the graph.
     * Note: this method run in O(1) time.
     * @return numofnodes.
     */
    @Override
    public int nodeSize() {
        return this.numOfNodes;
    }

    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method run in O(1) time.
     * @return numofEdges
     */

    @Override
    public int edgeSize() {
        return this.numOfEdges;
    }

    /**
     * Returns the Mode Count - for testing changes in the graph.
     * @return MC
     */
    @Override
    public int getMC() {
        return this.MC;
    }
}