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

    void walk()
    {}

    void collect()
    {}
}
