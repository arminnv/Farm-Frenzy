import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    double t = 0;
    double T = 1;
    double dt = Time.dt;
    BufferedImage image;
    BufferedImage subImage;
    int frames;
    int columns;
    int rows;

    Animation(int frames, int columns)
    {
        this.frames = frames;
        this.columns = columns;
    }

    BufferedImage getImage()
    {
        rows = frames/columns;
        if(frames%columns !=0 )
            rows++;

        t += dt;
        if(t >= T)
            T = 0;

        int frame = (int)(frames * t / T) + 1 ;
        int w = image.getWidth(null)/columns;
        int h = image.getHeight(null)/(frames/columns);
        int x = w * (frame/columns);
        int y = h * (frame/rows);
        x=0;
        y=0;
        subImage = image.getSubimage(x, y, 10, h);
        return image;
    }




}
