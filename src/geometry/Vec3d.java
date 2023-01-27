package src.geometry;
import static java.lang.Math.sqrt;

public class Vec3d {
    private double x;
    private double y;
    private double z;
    private double len;

    public static Vec3d getNull() {
        var cur = new Vec3d();
        cur.len = -1.;
        return cur;
    }

    public double get(int key) {
        switch(key) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
        }

        return 0.;
    }
    public double getLen() {
        return len;
    }

    public void set(int key, double val) {
        switch (key) {
            case 0:
                x = val;
                break;
            case 1:
                y = val;
                break;
            case 2:
                z = val;
                break;
        }
        update_len();
    }

    public Vec3d() {
        x = 0;
        y = 0;
        z = 0;
        update_len();
    }
    public Vec3d(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        update_len();
    }

    private void update_len() {
        this.len = sqrt(x * x + y * y + z * z);
    }
    public Vec3d normalize() {
        if (len == 0.) 
            return new Vec3d();
        return new Vec3d(x / len, y / len, z / len);
    }
    public Vec3d minus(Vec3d oth) {
        return new Vec3d(this.x - oth.get(0),
                     this.y - oth.get(1),
                     this.z - oth.get(2));
    }

    public Vec3d plus(Vec3d oth) {
        return new Vec3d(this.x + oth.get(0),
                         this.y + oth.get(1),
                         this.z + oth.get(2));
    }

    public double scal(Vec3d oth) {
        double cur = 0f;
        for(int i = 0; i < 3; i++) {
            cur += this.get(i) * oth.get(i);
        }
        return cur;
    }
    public Vec3d mult(double oth) {
        return new Vec3d(x * oth, y * oth, z * oth);
    }
}
