import java.util.ArrayList;
import java.util.Set;

public class Domestic extends Animal{

    static ArrayList<Domestic> list = new ArrayList<>();
    public static final Set<String> nameList=Set.of("chicken","turkey","buffalo");
    String productType;
    double health = 100;
    int productionTime;
    double timePassed = 0;
    int lifeReduction = 10;
    double dl = health * Time.dt * 0.2;
    Domestic(){}
    static Domestic newDomestic(String name, boolean addToMap)
    {
        Domestic d=new Domestic();
        d.h = 40;
        if(name.equals("chicken"))
        {
            d=new Domestic("chicken", 100,1,1,2,"egg");
            if (addToMap) {
                d.addToMap();
                d.number = animalIDNumHashMap.get("chicken");
            }
        }

        else if(name.equals("turkey"))
        {
            d=new Domestic("turkey", 200,1,1,3,"feather");
            if (addToMap) {
                d.addToMap();
                d.number = animalIDNumHashMap.get("turkey");
            }
        }

        else if(name.equals("buffalo"))
        {
            d=new Domestic("buffalo", 400,1,1,5,"milk");
            if (addToMap) {
                d.addToMap();
                d.number = animalIDNumHashMap.get("buffalo");
            }
        }
        return d;
    }
    Domestic(String type, int price, int step, int space, int productionTime, String productType){
        super(type,price,step,space);
        this.productionTime=productionTime;
        this.productType=productType;

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

        Domestic newDomestic = newDomestic(name,true);
        if(newDomestic.type.equals(""))
        {
            System.out.println("animal type not found");
            Logger.write('e',"animal type not found");
            return;
        }

        if(newDomestic.price <= Game.getCoins())
        {
            Game.addCoins(-newDomestic.price);
            Domestic.list.add(newDomestic);
            Task.claim(newDomestic.type);
            Logger.write('i',newDomestic.type + " has been bought");
            System.out.println(newDomestic.type + " has been bought");
        }
        else
        {
            System.out.println("not enough coins");
            Logger.write('e',"not enough coins");
        }
    }


    void eat()
    {
        if(health>50)
            return;
        if(x==6)
            x=5;
        if(y==6)
            y=5;
        if(Plant.num[(int)x][(int)y]>0)
        {
            Plant.num[(int)x][(int)y]--;
            health = 100;
            Logger.write('i', type+ " ate");
        }
    }

    void produce()
    {
        timePassed+=Time.dt;
        if(timePassed >= productionTime)
        {
            timePassed = 0;
            Product newProduct = Product.newProduct(productType);
            newProduct.x = x;
            newProduct.y = y;
            Product.list.add(newProduct);
            Logger.write('i',type + " produced " + productType);
        }
    }

    void kill()
    {
        Domestic.list.remove(this);
        removeFromMap(this.type);
        Logger.write('i', type + " died");
    }

    void walk()
    {
        double[] closest = {0,0};
        find(closest);
        if(health > 50 || closest[0] == 0)
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
        else
        {
            double x0 = closest[0];
            double y0 = closest[1];
            if(x0 > x && !this.intRange(x0,y))
            {
                x += dx;
                state = 1;
            }
            else if(x0 < x && !this.intRange(x0,y))
            {
                x -= dx;
                state = 3;
            }
            else if(y0 > y && !this.intRange(y0,x))
            {
                y += dx;
                state = 4;
            }
            else if(y0 < y && !this.intRange(y0,x))
            {
                y -= dx;
                state = 2;
            }
        }

    }

    static void reduce()
    {
        for(int i=0; i<Domestic.list.size(); i++) {
            Domestic.list.get(i).health -= Domestic.list.get(i).dl;
        }
        for(int i=0; i<Domestic.list.size(); i++) {
            if(Domestic.list.get(i).health<=0) {
                Logger.write('i', Domestic.list.get(i).type + " died");
                System.out.println(Domestic.list.get(i).type + " died");
                Domestic.list.remove(i);
                i--;
            }
        }

    }

    void find(double[] closest)
    {
        double min = 20;
        for (int i=0; i<6; i++)
        {
            for(int j=0; j<6; j++)
            {
                if(Plant.num[i][j] > 0)
                {
                    if(Math.abs((double) (i)+0.5-x) + Math.abs((double) (j)+0.5-y) < min)
                    {
                        closest[0] = (double) i+0.5;
                        closest[1] = (double)j+0.5;
                        min = Math.abs((double)i+0.5-x) + Math.abs((double)j+0.5-y);
                    }
                }
            }
        }
    }

}