package api;

public class Edge implements EdgeData{
    private int src;
    private int dest;
    private double weight;
    private int Tag;

    public Edge(int src ,int dest,double weight){
        this.src=src;
        this.dest=dest;
        this.weight=weight;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return this.Tag;
    }

    @Override
    public void setTag(int t) {
    this.Tag=t;
    }
}
