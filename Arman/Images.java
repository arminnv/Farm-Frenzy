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

    static BufferedImage main_back;
    static BufferedImage login_back;
    static BufferedImage pause;

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

    static BufferedImage[] mill=new BufferedImage[2];
    static BufferedImage[] weaver=new BufferedImage[2];
    static BufferedImage[] sewing=new BufferedImage[2];
    static BufferedImage[] milkPacking=new BufferedImage[2];
    static BufferedImage[] iceCreamFactory=new BufferedImage[2];
    static BufferedImage[] bakery=new BufferedImage[2];
    static BufferedImage[] incubator=new BufferedImage[2];

    static BufferedImage upgrade;

    static BufferedImage truck;
    static BufferedImage warehouse;
    static BufferedImage grass;
    static BufferedImage[] well=new BufferedImage[1];
    static BufferedImage land;

    static void load()
    {
        try
        {
            main_back=ImageIO.read(new File("mainb.jpg"));
            login_back=ImageIO.read(new File("login_back.jpg"));
            pause=ImageIO.read(new File("pause.jpg"));

            warehouse=ImageIO.read(new File("warehouse.png"));

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

            mill[0] = ImageIO.read(new File("factory/mill.png"));
            weaver[0] = ImageIO.read(new File("factory/weaver.png"));
            sewing[0] = ImageIO.read(new File("factory/sewing.png"));
            milkPacking[0] = ImageIO.read(new File("factory/milk.png"));
            iceCreamFactory[0] = ImageIO.read(new File("factory/ice cream.png"));
            bakery[0] = ImageIO.read(new File("factory/bakery1.png"));
            incubator[0] = ImageIO.read(new File("factory/incubator.png"));
            mill[1] = ImageIO.read(new File("factory/mill2.png"));
            weaver[1] = ImageIO.read(new File("factory/weaver2.png"));
            sewing[1] = ImageIO.read(new File("factory/sewing2.png"));
            milkPacking[1] = ImageIO.read(new File("factory/milk2.png"));
            iceCreamFactory[1] = ImageIO.read(new File("factory/ice cream2.png"));
            bakery[1] = ImageIO.read(new File("factory/bakery2.png"));
            incubator[1] = ImageIO.read(new File("factory/incubator2.png"));

            upgrade=ImageIO.read(new File("upgrade.png"));

            grass = ImageIO.read(new File("grass.png"));
            well[0] = ImageIO.read(new File("well.png"));
            truck = ImageIO.read(new File("truck.png"));
            land = ImageIO.read(new File("land.png"));
        }
        catch (IOException e){
            System.out.println("error loading images");
        }
    }
    public static BufferedImage getFactoryImage(String s){
        return switch (s) {
            case "weaver" -> weaver[0];
            case "sewing factory" -> sewing[0];
            case "bakery" -> bakery[0];
            case "ice cream factory" -> iceCreamFactory[0];
            case "hen incubator" -> incubator[0];
            case "mill" -> mill[0];
            case "milk packaging factory" -> milkPacking[0];
            default -> null;
        };
    }
    public static BufferedImage getAnimalImage(String s){
        return switch (s) {
            case "chicken" -> chicken[1];
            case "turkey" -> turkey[1];
            case "hound" -> hound[1];
            case "cat" -> cat[1];
            case "buffalo" -> buffalo[1];
            default -> null;
        };
    }
}
