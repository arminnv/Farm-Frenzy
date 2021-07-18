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
            };chicken = new BufferedImage[]{
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