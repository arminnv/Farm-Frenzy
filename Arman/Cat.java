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
            if((d==0)&&(x+step<=6))
                x += step;
            else if((d==1)&&(y+step<=6))
                y += step;
            else if((d==2)&&(x-step>=1))
                x -= step;
            else if((d==3)&&(y-step>=1))
                y -= step;
        }
        else
        {
            Product target = this.find();
            if(target.x > x)
                x++;
            else if(target.x < x)
                x--;
            else if(target.y > y)
                y++;
            else if(target.y < y)
                y--;
        }
    }

    void collect()
    {
        for (int i=0; i<Product.list.size(); i++)
        {
            Product product = Product.list.get(i);
            if(!product.collected)
            {
                if( (product.x == x && product.y == y) && ( Warehouse.getInstance().add(product) ) ) {
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
        int min = 20;
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
