import java.util.ArrayList;

public class Cat extends Animal{

    static ArrayList<Cat> list = new ArrayList<>();

    Cat(boolean addToMap)
    {
        super("cat",100,1,1);
        if (addToMap) {
            this.addToMap();
            number = animalIDNumHashMap.get("cat");
        }
    }

    static void buy()
    {
        Cat newCat = new Cat(false);
        if(newCat.price <= Game.getCoins())
        {
            newCat=new Cat(true);
            Game.addCoins(-newCat.price);
            Cat.list.add(newCat);
            Task.claim("cat");
            Logger.write('i', "cat has been bought");
        }
        else
        {
            System.out.println("not enough coins");
            Logger.write('e', "not enough coins");
        }
    }

    void kill()
    {
        Cat.list.remove(this);
        removeFromMap(this.type);
        Logger.write('i',"cat got killed");
    }

    void walk()
    {
        if(this.find() == null) {
            int d = randomDirection();
            if((d==0)&&(x+dx<=6))
                x += dx;
            else if((d==1)&&(y+dx<=6))
                y += dx;
            else if((d==2)&&(x-dx>=1))
                x -= dx;
            else if((d==3)&&(y-dx>=1))
                y -= dx;
        }
        else
        {
            Product target = this.find();
            if(target.x > x)
                x+=dx;
            else if(target.x < x)
                x-=dx;
            else if(target.y > y)
                y+=dx;
            else if(target.y < y)
                y-=dx;
        }
    }

    void collect()
    {
        for (int i=0; i<Product.list.size(); i++)
        {
            Product product = Product.list.get(i);
            if(!product.collected)
            {
                if( (this.intRange(product.x,product.y)) && ( Warehouse.getInstance().add(product) ) ) {
                    product.collected = true;///
                    Product.list.remove(product);//proposed
                    Task.claim(product.type);
                    Logger.write('i',"cat collected "+product.type);
                }
            }
        }
    }

    Product find()
    {
        double min = 20;
        Product closest = null;
        for (int i=0; i<Product.list.size(); i++)
        {
            Product product = Product.list.get(i);//cats collect caged wild animals
            if(!product.collected)
            {
                if(Math.abs(product.x-x) + Math.abs(product.y-y) < min)
                {
                    closest = product;
                    min = Math.abs(product.x-x) + Math.abs(product.y-y);
                }
            }
        }
        return closest;
    }
}