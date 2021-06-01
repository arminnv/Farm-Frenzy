import java.util.ArrayList;

public class Hound extends Animal{

    static ArrayList<Hound> list = new ArrayList<>();

    Hound()
    {
        type = "Hound";
        price = 100;
        step = 1;
        x = random();
        y = random();
    }

    static void buy()
    {
        Hound newHound = new Hound();
        if(newHound.price <= Game.coins)
        {
            Game.coins -= newHound.price;
            Hound.list.add(newHound);
        }
        else
            System.out.println("not enough coins");
    }

    void walk()
    {}

    void collect()
    {}
}
