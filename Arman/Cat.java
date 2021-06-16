import java.util.ArrayList;

public class Cat extends Animal{

    static ArrayList<Cat> list = new ArrayList<>();

    Cat()
    {
        type = "Cat";
        price = 100;
        step = 1;
        x = random();
        y = random();
        this.addToMap();
    }

    static void buy()
    {
        Cat newCat = new Cat();
        if(newCat.price <= Game.coins)
        {
            Game.coins -= newCat.price;
            Cat.list.add(newCat);
            Task.claim("Cat");
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
        Logger.write('i',"cat got killed");
    }

    void walk()
    {
        if(this.find() == null)
        {
            int d = randomDirection();
            if((d==0)&&(x+step<=6))
                x += step;
            else if((d==1)&&(y+step<=6))
                y += step;
            else if((d==2)&&(x-step>=1))
                x -= step;
            else if((d==3)&&(y-step>=1))
                y -= step;
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
            Product product = Product.list.get(i);
            if((!product.collected) && (!product.type.equals("Tiger")) && (!product.type.equals("Bear")) && (!product.type.equals("Lion")) )
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
