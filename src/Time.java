public class Time {

    static int time = 0;

    static void turn(int n)
    {
        Time.time += n;
        for(int i=0; i<n; i++)
            update();
    }

    static void update()
    {
        eat();
        Domestic.reduce();
        walk();
        eat();
        Product.expire();
        produce();
        removeCage();
    }

    static void walk()
    {
        for(int i=0; i<Domestic.list.size(); i++)
        {
            Domestic domestic = Domestic.list.get(i);
            domestic.walk();
        }

        for(int i=0; i<Cat.list.size(); i++)
        {
            Cat cat = Cat.list.get(i);
            cat.walk();
        }

        for(int i=0; i<Hound.list.size(); i++)
        {
            Hound hound = Hound.list.get(i);
            hound.walk();
        }

        for(int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            wild.walk();
        }
    }

    static void eat()
    {
        for (int i=0; i<Domestic.list.size(); i++)
        {
            Domestic.list.get(i).eat();
        }
    }

    static void produce()
    {
        for (int i=0; i<Domestic.list.size(); i++)
        {
            Domestic.list.get(i).produce();
        }
    }

    static void removeCage()
    {
        for(int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            if(wild.leftCages < wild.cages)
                wild.leftCages++;
        }
    }

}