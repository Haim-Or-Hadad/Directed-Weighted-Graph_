package api;

import java.util.HashMap;

public class Node implements NodeData {

        private GeoLocation coordinates;
        private int id;
        private  double weight=0;
        private  String Info;
        private  int Tag;
        private HashMap<Integer, Double> min_dist_from;

        public Node(int id , GeoLocation coord){
                this.id=id;
                this.coordinates=coord;
                this.Info="White";
                this.Tag=-1;
                min_dist_from=new HashMap<>();
        }

        public int get_min_dist_size(){
                return min_dist_from.size();
        }
        public Double getfrom_min(int key){
                if (min_dist_from.containsKey(key)){
                        return min_dist_from.get(key);
                }
                return Double.MAX_VALUE;

        }
        public void min_update(int from,double weight){
                if (min_dist_from.containsKey(from)){
                        if(weight<min_dist_from.get(from)){
                                min_dist_from.remove(from);
                                min_dist_from.put(from,weight);
                        }
                }
                else
                        min_dist_from.put(from,weight);
        }
        @Override
        public int getKey() {
                return this.id;
        }

        @Override
        public GeoLocation getLocation() {
                return this.coordinates;
        }

        @Override
        public void setLocation(GeoLocation p) {
        this.coordinates=new geo_location(p.x(),p.y(),p.z());
        }

        @Override
        public double getWeight() {
                return weight;
        }

        @Override
        public void setWeight(double w) {
        this.weight=w;
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
                return Tag;
        }

        @Override
        public void setTag(int t) {
        this.Tag=t;
        }
}
