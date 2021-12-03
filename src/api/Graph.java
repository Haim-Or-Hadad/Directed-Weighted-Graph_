package api;

import java.util.Iterator;
import java.util.List;
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
            int CurrNodeKey=N.next().getKey();
            E=G.edgeIter(CurrNodeKey);
            nodes.put(CurrNodeKey,N.next());
            this.numOfNodes++;
            while (E.hasNext()){
                HashMap<Integer, EdgeData> temp= new HashMap<>();
                temp.put(E.next().getDest(),E.next());
                this.numOfEdges++;
                if (!E.hasNext()){
                    edges.put(CurrNodeKey,temp);
                }
            }
        }
    }



    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
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
        Iterator<NodeData> nodeItr=this.nodeIter(); //Iterator that run over the nodes.
        HashMap<Integer,EdgeData> newE=new HashMap<>();
        Iterator<EdgeData>edge;  //Iterator that run over the edges of a specific node
        while (nodeItr.hasNext()){
            edge=this.edgeIter(nodeItr.next().getKey()); //Iterator get a node to run over
            while (edge.hasNext()) {
                newE.put(nodeItr.next().getKey(),edge.next()); //put the edge in a new Hashmap with the original key.
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
            int numofedges = this.edges.size();
            this.edges.remove(key);
            this.numOfEdges=this.numOfEdges-numofedges;
            this.MC=this.MC-numofedges;
            return this.nodes.remove(key);
            /*
            פה צריך לעבור על הedges ולמחוק את כל הצלעות שמגיעות לקודקוד הזה
             */
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