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
    int timePassed = 0;
    int lifeReduction = 10;
    boolean hungry = false;

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
    {
        if(health>50)
            return;
        if(Plant.num[x-1][y-1]>0)
        {
            Plant.num[x-1][y-1]--;
            health = 100;
        }
    }

    void produce()
    {
        timePassed++;
        if(timePassed >= productionTime)
        {
            timePassed = 0;
            Product newProduct = new Product(productType);
            newProduct.x = x;
            newProduct.y = y;
            Product.list.add(newProduct);
        }
    }

    void kill()
    {
        Domestic.list.remove(this);
    }

    void walk()
    {
        int d = randomDirection();
        if(d==0)
            x += step;
        else if(d==1)
            y += step;
        else if(d==2)
            x -= step;
        else if(d==3)
            y -= step;

        if(x>6)
            x=6;
        if(x<1)
            x=1;
        if(y>6)
            y=6;
        if(y<1)
            y=1;
    }

    static void reduce()
    {
        for(int i=0; i<Domestic.list.size(); i++)
        {
            Domestic.list.get(i).health -= Domestic.list.get(i).lifeReduction;
        }

        boolean b = true;
        while(b)
        {
        b = false;

        for(int i=0; i<Domestic.list.size(); i++)
        {
            if(Domestic.list.get(i).health<=0)
            {
                Domestic.list.remove(i);
                b = true;
                break;
            }
        }
        }
    }





}
