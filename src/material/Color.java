package src.material;

import src.geometry.Vec3d;

public class Color extends Vec3d {
    public static Color SkyBlue        = new Color(135, 206, 235);
    public static Color Grey           = new Color(190, 190, 190);
    public static Color Red            = new Color(255, 0, 0);
    public static Color Coral          = new Color(255, 127, 80);
    public static Color MediumPurple   = new Color(159, 121, 238);


    public Color(int r, int g, int b) {
        super((double)r / 255,
              (double)g / 255, 
              (double)b / 255);
    }    

}
