import java.util.ArrayList;

public class Cat extends Animal{

    static ArrayList<Cat> list = new ArrayList<>();

    Cat()
    {
        type = "Cat";
        price = 100;
        step = 1;
        x = random();
        y = random();
    }

    static void buy()
    {
        Cat newCat = new Cat();
        if(newCat.price <= Game.coins)
        {
            Game.coins -= newCat.price;
            Cat.list.add(newCat);
        }
        else
            System.out.println("not enough coins");
    }

    void kill()
    {
        Cat.list.remove(this);
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

    void collect()
    {}
}
