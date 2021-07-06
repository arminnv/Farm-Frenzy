   // static java.awt.Image image;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Images {

    static  File[] cowF = new File[7];
    static  File[] turkeyF = new File[7];
    static  File[] buffaloF = new File[7];
    static  File[] catF = new File[6];
    static  File[] houndF = new File[6];
    static  File[] lionF = new File[6];
    static  File[] bearF = new File[6];
    static  File[] tigerF = new File[6];

    static  File eggF = new File("product/egg.png");
    static  File featherF = new File("product/feather.png");
    static  File milkF = new File("product/milk.png");

    static  File grassF = new File("grass.png");
    static  File wellF = new File("well.png");
    static  File landF = new File("land.png");


    static Image[] cow = new Image[7];
    static Image[] turkey = new Image[7];
    static Image[] buffalo = new Image[7];
    static Image[] cat = new Image[6];
    static Image[] hound = new Image[6];
    static Image[] lion = new Image[6];
    static Image[] bear = new Image[6];
    static Image[] tiger = new Image[6];

    static Image egg;
    static Image feather;
    static Image milk;

    static Image grass;
    static Image well;
    static Image land;

    static void load()
    {
        try
        {
            for(int i=0; i<7; i++)
            {
                cow[i] = ImageIO.read(cowF[i]);
                turkey[i] = ImageIO.read(turkeyF[i]);
                buffalo[i] = ImageIO.read(buffaloF[i]);
            }
            for(int i=0; i<6; i++)
            {
                cat[i] = ImageIO.read(catF[i]);
                hound[i] = ImageIO.read(houndF[i]);
                lion[i] = ImageIO.read(lionF[i]);
                bear[i] = ImageIO.read(bearF[i]);
                tiger[i] = ImageIO.read(tigerF[i]);
            }

            egg = ImageIO.read(eggF);
            feather = ImageIO.read(featherF);
            milk = ImageIO.read(milkF);

            grass = ImageIO.read(grassF);
            well = ImageIO.read(wellF);
            land = ImageIO.read(landF);
        }
        catch (Exception e){
            System.out.println("error loading images");
        }
    }

    static void initialize()
    {
        cowF[0] = new File("domestic/cow/steady.png");
        cowF[1] = new File("domestic/cow/right.png");
        cowF[2] = new File("domestic/cow/down.png");
        cowF[3] = new File("domestic/cow/left.png");
        cowF[4] = new File("domestic/cow/up.png");
        cowF[5] = new File("domestic/cow/eat.png");
        cowF[6] = new File("domestic/cow/dead.png");

        turkeyF[0] = new File("domestic/turkey/steady.png");
        turkeyF[1] = new File("domestic/turkey/right.png");
        turkeyF[2] = new File("domestic/turkey/down.png");
        turkeyF[3] = new File("domestic/turkey/left.png");
        turkeyF[4] = new File("domestic/turkey/up.png");
        turkeyF[5] = new File("domestic/turkey/eat.png");
        turkeyF[6] = new File("domestic/turkey/dead.png");

        buffaloF[0] = new File("domestic/buffalo/steady.png");
        buffaloF[1] = new File("domestic/buffalo/right.png");
        buffaloF[2] = new File("domestic/buffalo/down.png");
        buffaloF[3] = new File("domestic/buffalo/left.png");
        buffaloF[4] = new File("domestic/buffalo/up.png");
        buffaloF[5] = new File("domestic/buffalo/eat.png");
        buffaloF[6] = new File("domestic/buffalo/dead.png");

        catF[0] = new File("cat/steady.png");
        catF[1] = new File("cat/right.png");
        catF[2] = new File("cat/down.png");
        catF[3] = new File("cat/left.png");
        catF[4] = new File("cat/up.png");
        catF[5] = new File("cat/pick.png");

        houndF[0] = new File("hound/steady.png");
        houndF[1] = new File("hound/right.png");
        houndF[2] = new File("hound/down.png");
        houndF[3] = new File("hound/left.png");
        houndF[4] = new File("hound/up.png");
        houndF[5] = new File("hound/attack.png");

        lionF[0] = new File("wild/lion/steady.png");
        lionF[1] = new File("wild/lion/right.png");
        lionF[2] = new File("wild/lion/down.png");
        lionF[3] = new File("wild/lion/left.png");
        lionF[4] = new File("wild/lion/up.png");
        lionF[5] = new File("wild/lion/attack.png");

        bearF[0] = new File("wild/bear/steady.png");
        bearF[1] = new File("wild/bear/right.png");
        bearF[2] = new File("wild/bear/down.png");
        bearF[3] = new File("wild/bear/left.png");
        bearF[4] = new File("wild/bear/up.png");
        bearF[5] = new File("wild/bear/attack.png");

        tigerF[0] = new File("wild/tiger/steady.png");
        tigerF[1] = new File("wild/tiger/right.png");
        tigerF[2] = new File("wild/tiger/down.png");
        tigerF[3] = new File("wild/tiger/left.png");
        tigerF[4] = new File("wild/tiger/up.png");
        tigerF[5] = new File("wild/tiger/attack.png");
    }
}
