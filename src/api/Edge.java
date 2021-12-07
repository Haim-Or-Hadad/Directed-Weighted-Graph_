package api;

public class Edge implements EdgeData{
    private final int src;
    private final int dest;
    private double weight;
    private int Tag;
    private String Info;

    public Edge(int src ,int dest,double weight){
        this.src=src;
        this.dest=dest;
        this.weight=weight;
        this.Info="White";
        this.Tag=-1;
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
        return Info;
    }

    @Override
    public void setInfo(String s) {
    this.Info=s;
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
