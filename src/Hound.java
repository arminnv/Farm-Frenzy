
import java.util.ArrayList;

public class Hound extends Animal{

    static ArrayList<Hound> list = new ArrayList<>();

    Hound()
    {
        type = "hound";
        price = 100;
        step = 1;
        x = random();
        y = random();
        space=1;
        this.addToMap();
        number= animalIDNumHashMap.get("hound");
    }

    static void buy()
    {
        Hound newHound = new Hound();
        if(newHound.price <= Game.coins)
        {
            Game.addCoins(-newHound.price);
            Hound.list.add(newHound);
            Task.claim("hound");
            Logger.write('i',"hound has been bought");
        }
        else
        {
            System.out.println("not enough coins");
            Logger.write('e',"not enough coins");
        }
    }

    void kill()
    {
        Hound.list.remove(this);
        removeFromMap(this.type);
        Logger.write('i',"hound got killed");
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
}