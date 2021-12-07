package api;

import java.util.Iterator;
import java.util.HashMap;

public class Graph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private int numOfNodes=0;
    private int numOfEdges=0;
    private int MC=0;

    public Graph(){
        this.nodes=new HashMap<>();
        this.edges=new HashMap<>();
        this.numOfNodes=0;
        this.numOfEdges=0;
        this.MC=0;
    }
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



    @Override
    public NodeData getNode(int key) {
        if(nodes.containsKey(key))
        return nodes.get(key);
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (!edges.containsKey(src)|| !edges.get(src).containsKey(dest))
            return null;
        else return edges.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(),n);
        edges.put(n.getKey(),new HashMap<>());
        this.numOfNodes++;
        this.MC++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData newEdge=new Edge(src,dest,w);
        edges.get(src).put(dest,newEdge);
        this.numOfEdges++;
        this.MC++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();

    }

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

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return edges.get(node_id).values().iterator();
    }

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

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(!this.edges.containsKey(src) || !this.edges.get(src).containsKey(dest))
            return null;
        EdgeData removedEdge=this.edges.get(src).remove(dest);
        this.MC++;
        this.numOfEdges--;
        return removedEdge;
    }

    @Override
    public int nodeSize() {
        return this.numOfNodes;
    }

    @Override
    public int edgeSize() {
        return this.numOfEdges;
    }

    @Override
    public int getMC() {
        return this.MC;
    }
}