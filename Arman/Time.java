public class Time {

    static int time = 0;

    static void turn(int n) {
        for(int i=0; i<n; i++) {
            update();
            Time.time++;
        }
        Game.myClock.getLocalTime();
    }

    static void update() {
        appear();
        eat();
        Domestic.reduce();
        walk();
        eat();
        Product.expire();
        produce();
        collect();
        factoryUpdate();
        Truck.getInstance().update();
        removeCage();
        Well.getInstance().fillingProcess();
        Plant.warn();
        Output.show();
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
            if( (wild.leftCages < wild.cages)&&(!wild.consecutiveCageOrder) )
                wild.leftCages++;
            wild.consecutiveCageOrder=false;
        }
    }

    static void collect()
    {
        for(int i=0; i<Cat.list.size(); i++)
        {
            Cat.list.get(i).collect();
        }
    }

    static void appear()
    {
        if(Game.mission.wilds.get(time) != null)
        {
            Wild.add(Game.mission.wilds.get(time));
        }
    }
    static void factoryUpdate(){
        for (Factory factory:Factory.factories){
            factory.update();
        }
    }

}
