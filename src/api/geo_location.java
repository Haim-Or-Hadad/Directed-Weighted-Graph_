package api;

public class geo_location implements GeoLocation {
    private double x;
    private double y;
    private double z;

    public geo_location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public geo_location(GeoLocation g){
        this.x= g.x();
        this.y=g.y();
        this.z=g.z();
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
        return Math.sqrt(Math.pow(x - g.x(), 2) + Math.pow((y - g.y()), 2) + Math.pow((z - g.z()), 2));
    }
}
