package src.geometry;

public class Vec3f {
    public float x;
    public float y;
    public float z;

    public float get(int i) {
        if (i == 0) return x;
        if (i == 1) return y;
        if (i == 2) return z;
        return 0f;
    }

    public Vec3f() {
        x = 0;
        y = 0;
        z = 0;
    }
    public Vec3f(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
}
