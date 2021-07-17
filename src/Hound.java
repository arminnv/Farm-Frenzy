import java.util.ArrayList;

public class Hound extends Animal{

    static ArrayList<Hound> list = new ArrayList<>();

    Hound()
    {
        super("hound",100,1,1,60,60);
        this.addToMap();
        number= animalIDNumHashMap.get("hound");
    }

    static void buy()
    {
        Hound newHound = new Hound();
        if(newHound.price <= Game.getCoins())
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
        t-=Time.dt;
        if(t<0)
        {
            d = randomDirection();
            t=1;
        }
        if(d==1)
        {
            x += dx;
            state = 1;
        }
        else if(d==2)
        {
            y -= dx;
            state = 2;
        }
        else if(d==3)
        {
            x -= dx;
            state = 3;
        }
        else if(d==4)
        {
            y += dx;
            state = 4;
        }
        if(x>6)
        {x=6; t=1; d = randomD(d);}
        if(x<0)
        {x=0; t=1; d = randomD(d);}
        if(y>6)
        {y=6; t=1; d = randomD(d);}
        if(y<0)
        {y=0; t=1; d = randomD(d);}
    }
}