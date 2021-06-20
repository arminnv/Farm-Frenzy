import java.util.ArrayList;
import java.util.Set;

public class Wild extends Animal{

    static ArrayList<Wild> list = new ArrayList<>();
    public static final Set<String> nameList=Set.of("bear","tiger","lion");

    int cages;
    int leftCages;
    boolean consecutiveCageOrder;

    Wild(String name)
    {
        consecutiveCageOrder=false;
        name = name.toLowerCase();
        if(name.equals("lion"))
        {
            type = "lion";
            price = 300;
            step = 1;
            space =15;
            cages = 3;
            leftCages = 3;
            x = random();
            y = random();
        }

        else if(name.equals("bear"))
        {
            type = "bear";
            price = 400;
            step = 1;
            space =15;
            cages = 4;
            leftCages = 4;
            x = random();
            y = random();
        }

        else if(name.equals("tiger"))
        {
            type = "tiger";
            price = 500;
            step = 2;
            space =1;
            cages = 4;
            leftCages = 4;
            x = random();
            y = random();
        }
    }

    static void add(String name)
    {
        Wild.list.add(new Wild(name));
    }

    void kill()
    {
        Wild.list.remove(this);
        Logger.write('i',type+" got killed");
    }

    void walk()
    {
        int d = randomDirection();
        for(int k=1; k<=step; k++)
        {

            if((d==0)&&(x<6))
                x++;
            else if((d==1)&&(y<6))
                y++;
            else if((d==2)&&(x>1))
                x--;
            else if((d==3)&&(y>1))
                y--;
            /*
            if(x>6)
                x=6;
            if(x<1)
                x=1;
            if(y>6)
                y=6;
            if(y<1)
                y=1;
            */
            for(int j=0; j<Hound.list.size(); j++)
            {
                Hound hound = Hound.list.get(j);
                if(hound.x == x && hound.y == y)
                {
                    hound.kill();
                    kill();
                    return;
                }
            }

            for(int j=0; j<Domestic.list.size(); j++)
            {
                Domestic domestic = Domestic.list.get(j);
                if(domestic.x == x && domestic.y == y)
                {
                    domestic.kill();
                    break;
                }
            }

            for(int j=0; j<Cat.list.size(); j++)
            {
                Cat cat = Cat.list.get(j);
                if(cat.x == x && cat.y == y)
                {
                    cat.kill();
                    break;
                }
            }


        }
    }

    static void cage(int x, int y)
    {
        for (int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            if(wild.x == x && wild.y == y)
            {
                wild.consecutiveCageOrder=true;
                wild.leftCages--;
                if(wild.leftCages<=0)
                {
                    Logger.write('i',Wild.list.get(i).type+" got caged");
                    Product newProduct = new Product(wild.type);
                    newProduct.x = wild.x;
                    newProduct.y = wild.y;
                    newProduct.collected = false;
                    Product.list.add(newProduct);
                    Wild.list.remove(wild);
                }
                return;
            }
        }
    }
}
