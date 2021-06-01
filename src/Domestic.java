import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class Domestic extends Animal{

    static ArrayList<Domestic> list = new ArrayList<>();
    static HashMap<String, String> domesticProduct = new HashMap<String, String>() {{
        put("Chicken", "Egg");
        put("Turkey", "Feather");
        put("Buffalo", "Milk");
    }};

    String productType;
    int health = 100;
    int productionTime;
    int lifeReduction = 10;
    boolean hungry = false;

    Domestic()
    {}

    Domestic(String name)
    {
        if(name.equals("chicken"))
        {
            type = "Chicken";
            productionTime = 2;
            productType = "Egg";
            price = 100;
            step = 1;
            x = random();
            y = random();
        }

        else if(name.equals("turkey"))
        {
            type = "Turkey";
            productionTime = 3;
            productType = "Feather";
            price = 200;
            step = 1;
            x = random();
            y = random();
        }

        else if(name.equals("buffalo"))
        {
            type = "Buffalo";
            productionTime = 5;
            productType = "Milk";
            price = 400;
            step = 1;
            x = random();
            y = random();
        }
    }

    static void buy(String name)
    {
        name = name.toLowerCase();

        if(name.equals("cat"))
        {
            Cat.buy();
            return;
        }
        else if(name.equals("hound"))
        {
            Hound.buy();
            return;
        }

        Domestic newDomestic = new Domestic(name);
        if(newDomestic.type.equals(""))
        {
            System.out.println("animal type not found");
            return;
        }

        if(newDomestic.price <= Game.coins)
        {
            Game.coins -= newDomestic.price;
            Domestic.list.add(newDomestic);
        }
        else
            System.out.println("not enough coins");
    }


    void eat()
    {}

    void produce()
    {}

    void walk()
    {}

    void die()
    {}



}
