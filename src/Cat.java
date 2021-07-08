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
            Product target = this.find();
            if(target.x > x && !this.intRange(target.x,y))
            {
                x += dx;
                state = 1;
            }
            else if(target.x < x && !this.intRange(target.x,y))
            {
                x -= dx;
                state = 3;
            }
            else if(target.y > y && !this.intRange(target.y,x))
            {
                y += dx;
                state = 4;
            }
            else if(target.y < y && !this.intRange(target.y,x))
            {
                y -= dx;
                state = 2;
            }
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