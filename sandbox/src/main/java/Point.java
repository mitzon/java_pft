public class Point {

    String id;
    public double x;
    public double y;

    public Point(String id, double x, double y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public double distanceToAnotherPoint(Point p){
        return Math.sqrt(square(p.x - this.x) + square(p.y - this.y));
    }

    static double square(double a){
        double result = a * a;
        return result;
    }
}
