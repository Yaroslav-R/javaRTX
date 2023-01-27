package src.geometry;
import static java.lang.Math.sqrt;

public class Sphere {
    private Vec3d center;
    private double radius;
    private Vec3d color;
    
    public static Sphere getNull() {
        var cur = new Sphere();
        cur.radius = -1;
        return cur;
    }

    public Sphere() {}

    public Sphere(Vec3d center, double radius, Vec3d color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }
    
    
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return this.radius;
    }
    public void setCenter(Vec3d center) {
        this.center = center;
    }
    
    public void setColor(Vec3d color) {
        this.color = color;
    }

    public Vec3d getColor() {
        return color;
    }

    public Vec3d getCenter() {
        return center;
    }


    public Vec3d ray_intersect(Vec3d orig, Vec3d dir) {
        double sphere_dist = 0.;
        Vec3d L = center.minus(orig);
        double tca = L.scal(dir);
        double d2 = L.scal(L) - tca * tca;

        if (d2 > radius) return Vec3d.getNull();
        double thc = sqrt(radius * radius - d2);
        sphere_dist = tca - thc;
        double t1 = tca + thc;
        if (sphere_dist < 0.) sphere_dist = t1;
        if (sphere_dist < 0.) return Vec3d.getNull();


        return orig.plus(dir.mult(sphere_dist));
    }
}
