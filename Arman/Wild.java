import java.util.ArrayList;
import java.util.Set;

public class Wild extends Animal{

    static ArrayList<Wild> list = new ArrayList<>();
    public static final Set<String> nameList=Set.of("bear","tiger","lion");

    int cages;
    int leftCages;
    boolean consecutiveCageOrder;
    Wild(String name, int price, int step, int space, int cages, int leftCages){
        super(name,price,step,space);
        this.cages=cages;
        this.leftCages=leftCages;
        this.consecutiveCageOrder=false;
    }
    Wild(){}
    static Wild createWild(String name)
    {
        name = name.toLowerCase();
        if(name.equals("lion"))
            return new Wild("lion",300,1,15,3,3);
        else if(name.equals("bear"))
            return new Wild("bear",400,1,15,4,4);
        else if(name.equals("tiger"))
            return new Wild("bear",500,2,15,4,4);
        return new Wild();
    }

    static void add(String name)
    {
        Wild.list.add(createWild(name));
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
                    Product newProduct = Product.newProduct(wild.type);
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
