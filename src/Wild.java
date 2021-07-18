import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

public class Wild extends Animal{

    static ArrayList<Wild> list = new ArrayList<>();
    public static final Set<String> nameList=Set.of("bear","tiger","lion");

    int cages;
    int leftCages;
    double t = 1;
    boolean consecutiveCageOrder;
    BufferedImage[] images;
    private Wild(String name, int price, int step, int space, int cages, int leftCages, int h, int w){
        super(name,price,step,space,h,w);
        this.cages=cages;
        images=new BufferedImage[cages];
        for (int i=0;(i<cages)&&(i<4);i++){
            images[i]=Images.cage[i];
        }
        this.leftCages=leftCages;
        this.consecutiveCageOrder=false;
        t = 1;
    }
    Wild(){}
    static Wild createWild(String name)
    {
        name = name.toLowerCase();
        if(name.equals("lion"))
            return new Wild("lion",300,1,15,3,3,80,80);
        else if(name.equals("bear"))
            return new Wild("bear",400,1,15,4,4,90,90);
        else if(name.equals("tiger"))
            return new Wild("tiger",500,2,15,4,4,60,60);
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
        t-=Time.dt;
        if(t<0)
        {
            if(type.equals("tiger"))
                d= randomD(d);
            else
                d = randomDirection();
            t=1;
        }


        for(int k=1; k<=step; k++)
        {
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

            for(int j=0; j<Hound.list.size(); j++)
            {
                Hound hound = Hound.list.get(j);
                if(this.intRange(hound.x,hound.y))
                {
                    hound.kill();
                    kill();
                    return;
                }
            }

            for(int j=0; j<Domestic.list.size(); j++)
            {
                Domestic domestic = Domestic.list.get(j);
                if(this.intRange(domestic.x,domestic.y))
                {
                    domestic.kill();
                    break;
                }
            }

            for(int j=0; j<Cat.list.size(); j++)
            {
                Cat cat = Cat.list.get(j);
                if(this.intRange(cat.x,cat.y))
                {
                    cat.kill();
                    break;
                }
            }


        }
    }

    static void cage(double x, double y)
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

    void cage()
    {
        Wild wild = this;
        wild.consecutiveCageOrder=true;
        wild.leftCages--;
        if(wild.leftCages<=0)
        {
            Logger.write('i',wild.type+" got caged");
            Product newProduct = Product.newProduct(wild.type);
            newProduct.x = wild.x;
            newProduct.y = wild.y;
            newProduct.collected = false;
            Product.list.add(newProduct);
            Wild.list.remove(wild);
        }
    }
    @Override
    boolean intRange(double X, double Y)
    {
        return Math.abs(x - X) < 0.5 && Math.abs(y - Y) < 0.5;
    }
    boolean mouseIntRange(double X, double Y)
    {
        return Math.abs(x - X) < 0.7 && Math.abs(y - Y) < 0.7;
    }
}