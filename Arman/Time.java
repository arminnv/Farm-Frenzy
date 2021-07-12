public class Time {

    static double time = 0;
    static double dt = 0.005;

    static void turn(int n) {
        for(int i=0; i<n; i++) {
            update();
        }
        Game.myClock.getLocalTime();
    }

    static void update() {
        Time.time+=dt;
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
        //Output.show();
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
            if( (wild.leftCages < wild.cages)&&(!wild.consecutiveCageOrder)  )
            {
                if(wild.t<0)
                {
                wild.leftCages++;
                wild.t=1;
                }
                else
                {
                    wild.t -= dt;
                }

            }
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
        int t = (int) time;
        if(Game.mission.wilds.get(t) != null)
        {
            Wild.add(Game.mission.wilds.get(t));
            Game.mission.wilds.put(t, null);
        }
    }
    static void factoryUpdate(){
        for (Factory factory:Factory.factories){
            factory.update();
        }
    }

}