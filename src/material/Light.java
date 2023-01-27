package src.material;

import src.geometry.Vec3d;

public class Light {
    private Vec3d pos;
    private double inten;

    public Light(Vec3d pos, double inten) {
        this.pos = pos;
        this.inten = inten;
    }
    public Vec3d getPos() {
        return pos;
    }
    public double getInten() {
        return inten;
    }
}
