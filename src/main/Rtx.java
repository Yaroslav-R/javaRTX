package src.main;

import static java.lang.Math.max;
import static java.lang.Math.min;
import src.geometry.Vec3f;
import java.io.*;

public class Rtx{
    public static void render() {
        int width  = 1024;
        int heigth = 768;
        
        Vec3f[] framebuffer = new Vec3f[width * heigth];
        
        for (int j = 0; j < heigth; j++) {
            for (int i = 0; i < width; i++) {
                framebuffer[i + j * width] = new Vec3f((float)j/(float)(heigth), (float)i/(float)(width), 0.0f);

            }
        }

        String Header = "P6\n" + width + " " + heigth + "\n255\n";

        byte[] out = new byte[framebuffer.length * 3];
        int it = 0;
        for (int i = 0; i < framebuffer.length; i++) {
            for (int j = 0; j < 3; j++) {
                byte cur = (byte)(255 * (max(0.0f, min(1.0f, framebuffer[i].get(j)))));
                out[it++] = cur;
            }
        }

        try (FileOutputStream stream = new FileOutputStream("out.ppm")) {
            stream.write(Header.getBytes());
            stream.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        render();
        
        return;
    } 
}
