   // static java.awt.Image image;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

   public class Images {

       int w;
       int h;

    static BufferedImage[] chicken = new BufferedImage[6];
    static BufferedImage[] turkey = new BufferedImage[6];
    static BufferedImage[] buffalo = new BufferedImage[6];
    static BufferedImage[] cat = new BufferedImage[5];
    static BufferedImage[] hound = new BufferedImage[5];
    static BufferedImage[] lion = new BufferedImage[5];
    static BufferedImage[] bear = new BufferedImage[5];
    static BufferedImage[] tiger = new BufferedImage[5];

       static BufferedImage[] cage = new BufferedImage[4];

    static BufferedImage egg;
    static BufferedImage feather;
    static BufferedImage milk;
    static BufferedImage flour;
    static BufferedImage fabric;
    static BufferedImage packetMilk;
    static BufferedImage bread;
    static BufferedImage shirt;
    static BufferedImage iceCream;
    static BufferedImage cagedLion;
    static BufferedImage cagedBear;
    static BufferedImage cagedTiger;

    static BufferedImage mill;
    static BufferedImage weaver;
    static BufferedImage sewing;
    static BufferedImage milkPacking;
    static BufferedImage iceCreamFactory;
    static BufferedImage bakery;
    static BufferedImage incubator;

    static BufferedImage truck;

    static BufferedImage grass;
    static BufferedImage well;
    static BufferedImage land;

    static void load()
    {
        try
        {
            //chicken[0] = ImageIO.read(new File("domestic/chicken/eat.png"));
            chicken[1] = ImageIO.read(new File("domestic/chicken/right.png"));
            chicken[2] = ImageIO.read(new File("domestic/chicken/down.png"));
            chicken[3] = ImageIO.read(new File("domestic/chicken/left.png"));
            chicken[4] = ImageIO.read(new File("domestic/chicken/up.png"));
            //chicken[5] = ImageIO.read(new File("domestic/chicken/dead.png"));

            //turkey[0] = ImageIO.read(new File("domestic/turkey/eat.png"));
            turkey[1] = ImageIO.read(new File("domestic/turkey/right.png"));
            turkey[2] = ImageIO.read(new File("domestic/turkey/down.png"));
            turkey[3] = ImageIO.read(new File("domestic/turkey/left.png"));
            turkey[4] = ImageIO.read(new File("domestic/turkey/up.png"));
            //turkey[5] = ImageIO.read(new File("domestic/turkey/dead.png"));

            //buffalo[0] = ImageIO.read(new File("domestic/buffalo/eat.png"));
            buffalo[1] = ImageIO.read(new File("domestic/buffalo/right.png"));
            buffalo[2] = ImageIO.read(new File("domestic/buffalo/down.png"));
            buffalo[3] = ImageIO.read(new File("domestic/buffalo/left.png"));
            buffalo[4] = ImageIO.read(new File("domestic/buffalo/up.png"));
            //buffalo[5] = ImageIO.read(new File("domestic/buffalo/dead.png"));

            cat[1] = ImageIO.read(new File("cat/right.png"));
            cat[2] = ImageIO.read(new File("cat/down.png"));
            cat[3] = ImageIO.read(new File("cat/left.png"));
            cat[4] = ImageIO.read(new File("cat/up.png"));

            hound[1] = ImageIO.read(new File("hound/right.png"));
            hound[2] = ImageIO.read(new File("hound/down.png"));
            hound[3] = ImageIO.read(new File("hound/left.png"));
            hound[4] = ImageIO.read(new File("hound/up.png"));

            lion[1] = ImageIO.read(new File("wild/lion/right.png"));
            lion[2] = ImageIO.read(new File("wild/lion/down.png"));
            lion[3] = ImageIO.read(new File("wild/lion/left.png"));
            lion[4] = ImageIO.read(new File("wild/lion/up.png"));

            bear[1] = ImageIO.read(new File("wild/bear/right.png"));
            bear[2] = ImageIO.read(new File("wild/bear/down.png"));
            bear[3] = ImageIO.read(new File("wild/bear/left.png"));
            bear[4] = ImageIO.read(new File("wild/bear/up.png"));

            tiger[1] = ImageIO.read(new File("wild/tiger/right.png"));
            tiger[2] = ImageIO.read(new File("wild/tiger/down.png"));
            tiger[3] = ImageIO.read(new File("wild/tiger/left.png"));
            tiger[4] = ImageIO.read(new File("wild/tiger/up.png"));

            cage[0] = ImageIO.read(new File("cage/0.png"));
            cage[1] = ImageIO.read(new File("cage/1.png"));
            cage[2] = ImageIO.read(new File("cage/2.png"));
            cage[3] = ImageIO.read(new File("cage/3.png"));

            egg = ImageIO.read(new File("product/egg.png"));
            feather = ImageIO.read(new File("product/feather.png"));
            milk = ImageIO.read(new File("product/milk.png"));

            flour = ImageIO.read(new File("product/flour.png"));
            fabric = ImageIO.read(new File("product/fabric.png"));
            packetMilk = ImageIO.read(new File("product/packet milk.png"));
            bread = ImageIO.read(new File("product/bread.png"));
            shirt = ImageIO.read(new File("product/shirt.png"));
            iceCream = ImageIO.read(new File("product/ice cream.png"));
            cagedLion = ImageIO.read(new File("product/lion.png"));
            cagedBear = ImageIO.read(new File("product/bear.png"));
            cagedTiger = ImageIO.read(new File("product/tiger.png"));

            mill = ImageIO.read(new File("factory/mill.png"));
            weaver = ImageIO.read(new File("factory/weaver.png"));
            sewing = ImageIO.read(new File("factory/sewing.png"));
            milkPacking = ImageIO.read(new File("factory/milk.png"));
            iceCreamFactory = ImageIO.read(new File("factory/ice cream.png"));
            bakery = ImageIO.read(new File("factory/bakery.png"));
            incubator = ImageIO.read(new File("factory/incubator.png"));

            grass = ImageIO.read(new File("grass.png"));
            well = ImageIO.read(new File("well.png"));
            truck = ImageIO.read(new File("truck.png"));
            land = ImageIO.read(new File("land.png"));
        }
        catch (IOException e){
            System.out.println("error loading images");
        }
    }
}
