import java.awt.*;
import java.awt.image.BufferedImage;

public class Frame {

    //double t = 0;
    double T = 0.2;
    double dt = Time.dt;
    BufferedImage image;
    BufferedImage subImage;
    int frames;
    int columns;
    int rows;

    Frame(int frames, int columns)
    {
        this.frames = frames;
        this.columns = columns;
    }

    BufferedImage getFrame(Animal a)
    {
        rows = frames/columns;
        if(frames%columns !=0 )
            rows++;

        a.t0 += dt;

        //System.out.println(frames+" "+a.t0/T);
        int frame =(int)(frames *(a.t0 / T)) +1 ;
        if(frame > frames)
        {
            a.t0 = 0;
            frame = 1 ;
        }
        if(a.type.equals("chicken") && frame==21 && a.state == 1)
            frame = 22;

        int w = image.getWidth(null)/columns;
        int h = image.getHeight(null)/rows;

        //w = 1;
        //h=1;

        int i = frame % columns;
        if(i==0)
            i=columns;
        int j = frame/columns + 1;
        if(j>rows)
            j=1;

        int x = w * (i-1);
        int y = h * (j-1);
        //System.out.println(i+" "+j);
       // x=0;
        //y=0;
        subImage = image.getSubimage(x, y, w, h);
        return subImage;
    }
     void set(int frames, int columns)
     {
         this.frames = frames;
         this.columns = columns;
     }




}
