package api;

public class Node implements NodeData,GeoLocation {

        private double x;
        private double y;
        private double z;
        private int id;

        public Node(double x , double y , double z ,int id){
                this.id=id;
                this.x=x;
                this.y=y;
                this.z=z;
        }

        @Override
        public double x() {
                return this.x;
        }

        @Override
        public double y() {
                return this.y;
        }

        @Override
        public double z() {
                return this.z;
        }

        @Override
        public double distance(GeoLocation g) {
                return Math.sqrt(Math.pow(x-g.x(),2)+Math.pow((y-g.y()),2)+Math.pow((z-g.z()),2));
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

        }

        @Override
        public double getWeight() {
                return 0;
        }

        @Override
        public void setWeight(double w) {

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
                return 0;
        }

        @Override
        public void setTag(int t) {

        }
}
