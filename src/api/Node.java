package api;

public class Node implements NodeData {

        GeoLocation coordinates;
        private int id;
        private double weight=0;
        private String Info;
        private int tag;

        public Node(int id , GeoLocation coord){
                this.id=id;
                this.coordinates=coord;
                this.Info="White";
                this.tag=-1;
        }


        @Override
        public int getKey() {
                return this.id;
        }

        @Override
        public GeoLocation getLocation() {
                return null;
        }

        @Override
        public void setLocation(GeoLocation p) {
        this.coordinates=new geo_location(p.x(),p.y(),p.z()) {
        };
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
                return tag;
        }

        @Override
        public void setTag(int t) {
        this.tag=t;
        }
}
