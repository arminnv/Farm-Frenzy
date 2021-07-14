   // static java.awt.Image image;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

   public class Images {

       int w;
       int h;

    static BufferedImage[] chicken;
    static BufferedImage[] turkey;
    static BufferedImage[] buffalo;
    static BufferedImage[] cat;
    static BufferedImage[] hound;
    static BufferedImage[] lion;
    static BufferedImage[] bear;
    static BufferedImage[] tiger;

    static BufferedImage[] cage;

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

    static BufferedImage grass;
    static BufferedImage well;
    static BufferedImage truck;
    static BufferedImage land;

   // static void

    static void load()
    {
        try
        {
            chicken = new BufferedImage[]{
                    ImageIO.read(new File("domestic/chicken/right.png")),
                    ImageIO.read(new File("domestic/chicken/down.png")),
                    ImageIO.read(new File("domestic/chicken/left.png")),
                    ImageIO.read(new File("domestic/chicken/up.png"))
            };

            turkey = new BufferedImage[]{
                    ImageIO.read(new File("domestic/turkey/right.png")),
                    ImageIO.read(new File("domestic/turkey/down.png")),
                    ImageIO.read(new File("domestic/turkey/left.png")),
                    ImageIO.read(new File("domestic/turkey/up.png"))
            };

            buffalo = new BufferedImage[]{
                    ImageIO.read(new File("domestic/buffalo/right.png")),
                    ImageIO.read(new File("domestic/buffalo/down.png")),
                    ImageIO.read(new File("domestic/buffalo/left.png")),
                    ImageIO.read(new File("domestic/buffalo/up.png"))
            };

            cat = new BufferedImage[]{
                    ImageIO.read(new File("cat/right.png")),
                    ImageIO.read(new File("cat/down.png")),
                    ImageIO.read(new File("cat/left.png")),
                    ImageIO.read(new File("cat/up.png"))
            };

            hound = new BufferedImage[]{
                    ImageIO.read(new File("hound/right.png")),
                    ImageIO.read(new File("hound/down.png")),
                    ImageIO.read(new File("hound/left.png")),
                    ImageIO.read(new File("hound/up.png"))
            };

            lion = new BufferedImage[]{
                    ImageIO.read(new File("wild/lion/right.png")),
                    ImageIO.read(new File("wild/lion/down.png")),
                    ImageIO.read(new File("wild/lion/left.png")),
                    ImageIO.read(new File("wild/lion/up.png"))
            };

            bear = new BufferedImage[]{
                    ImageIO.read(new File("wild/bear/right.png")),
                    ImageIO.read(new File("wild/bear/down.png")),
                    ImageIO.read(new File("wild/bear/left.png")),
                    ImageIO.read(new File("wild/bear/up.png"))
            };

            tiger = new BufferedImage[]{
                    ImageIO.read(new File("wild/tiger/right.png")),
                    ImageIO.read(new File("wild/tiger/down.png")),
                    ImageIO.read(new File("wild/tiger/left.png")),
                    ImageIO.read(new File("wild/tiger/up.png"))
            };

            cage = new BufferedImage[]{
                    ImageIO.read(new File("cage/0.png")),
                    ImageIO.read(new File("cage/1.png")),
                    ImageIO.read(new File("cage/2.png")),
                    ImageIO.read(new File("cage/3.png"))
            };

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
