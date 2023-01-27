package src.main;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.tan;
import static java.lang.Math.PI;

import src.geometry.Vec3d;
import src.geometry.Sphere;
import src.material.Light;
import src.material.Color;

import java.io.*;
import java.util.ArrayList;

public class Rtx{
    private static Color BgColor = Color.SkyBlue;

    public static Vec3d cast_ray(Vec3d orig, Vec3d dir, ArrayList<Sphere> spheres, ArrayList<Light> lights) {
        Sphere hitSphere = Sphere.getNull();
        Vec3d hit = new Vec3d();
        
        for (var sphere : spheres) {
            Vec3d _hit = sphere.ray_intersect(orig, dir);
            if (_hit.getLen() >= 0.) {
                hitSphere = sphere;
                hit = _hit;
            }
        }

        if (hitSphere.getRadius() < 0.) {
            return BgColor;
        }
        
        Vec3d N = hit.minus(hitSphere.getCenter()).normalize();
        double lightIntensivity = 0.0;
        for (var light : lights) {
            Vec3d light_dir = light.getPos().minus(hit).normalize();
            lightIntensivity += light.getInten() * max(0.,light_dir.scal(N));

        }

        return hitSphere.getColor().mult(lightIntensivity);
    }

    public static Vec3d[] render(int width, int heigth, double fov, ArrayList<Sphere> spheres, ArrayList<Light> lights) 
    {
        Vec3d[] framebuffer = new Vec3d[width * heigth];
        

        for (int j = 0; j < heigth; j++) {
            for (int i = 0; i < width; i++) {
                double x =  (2. * (i + 0.5)/(double)width  - 1.) * tan(fov/2.) * (double)width / (double)heigth;
                double y = -(2. * (j + 0.5)/(double)heigth - 1.) * tan(fov/2.);

                Vec3d dir = new Vec3d(x, y, -1f).normalize();

                framebuffer[i + j * width] = cast_ray(new Vec3d(0., 0., 0.),
                                                      dir, 
                                                      spheres,
                                                      lights);
            }
        }

        return framebuffer;
    }

    public static void main(String args[]) {
        int width   = 1024;
        int heigth  = 768;
        double fov = PI * 0.5;

        ArrayList<Sphere> spheres = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();
        

        spheres.add(new Sphere(new Vec3d(3., 0., -16), 2, Color.Coral));
        spheres.add(new Sphere(new Vec3d(-1., -1.5, -12), 2, Color.Grey));
        spheres.add(new Sphere(new Vec3d(1.5, -0.5, -18), 3, Color.Red));
        spheres.add(new Sphere(new Vec3d(-7, 5, -18), 4, Color.MediumPurple));

        lights.add(new Light(new Vec3d(-20, 20, 20), 1.5));
        
        Vec3d[] frame = render(width, heigth, fov, spheres, lights);


        String Header = "P6\n" + width + " " + heigth + "\n255\n";

        byte[] out = new byte[frame.length * 3];
        int it = 0;
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < 3; j++) {
                byte cur = (byte)(255 * (max(0.0, min(1.0, frame[i].get(j)))));
                out[it++] = cur;
            }
        }

        try (FileOutputStream stream = new FileOutputStream("out.ppm")) {
            stream.write(Header.getBytes());
            stream.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    } 
}
