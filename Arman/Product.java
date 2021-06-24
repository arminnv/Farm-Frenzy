import java.util.*;

public class Product {
    public static final Set<String> nameList=Set.of("egg", "feather", "milk", "flour", "fabric", "packet milk", "bread", "shirt", "ice cream", "lion", "bear", "tiger");
    static ArrayList<Product> list = new ArrayList<>();
    String type;
    int price;
    int expirationTime;
    int x;
    int y;
    boolean collected = false;
    int space;
    Product(){}
    Product(String name, int price,int expirationTime,int space){
        this.type=name;
        this.price=price;
        this.expirationTime=expirationTime;
        this.space=space;
    }
    static Product newProduct(String name)
    {
        if(name.equals("egg"))
            return new Product("egg",15,4,1);
        else if(name.equals("feather"))
            return new Product("feather",20,4,1);
        else if(name.equals("milk"))
            return new Product("milk",25,4,1);
        else if(name.equals("flour"))
            return new Product("flour",40,5,2);
        else if(name.equals("ّfabric"))
            return new Product("fabric",50,5,2);
        else if(name.equals("packet milk"))
            return new Product("packet milk",60,5,2);
        else if(name.equals("bread"))
            return new Product("bread",80,6,4);
        else if(name.equals("shirt"))
            return new Product("shirt",100,6,4);
        else if(name.equals("ice cream"))
            return new Product("ice cream",120,6,4);
        else if(name.equals("lion"))
            return new Product("lion",300,5,15);
        else if(name.equals("bear"))
            return new Product("bear",400,5,15);
        else if(name.equals("tiger"))
            return new Product("tiger",500,5,15);
        Product p=new Product();
        p.type=name;
        return p;
    }

    static void pickup(int x, int y)
    {
        for(int i=0; i<Product.list.size(); i++)
        {
            Product p=Product.list.get(i);
            if(p.x == x && p.y == y)
            {
                if (Warehouse.getInstance().add(p)) {
                    p.collected = true;
                    Task.claim(p.type);
                    Logger.write('i', p.type + " got picked up");
                    list.remove(p);//proposed
                }
                else {
                    Logger.write('e', "Warehouse did not have enough space");
                }
                return;
            }
        }
        System.out.println("product not found");
        Logger.write('e',"product not found");
    }

    static void expire()
    {
        for(int i=0; i<Product.list.size(); i++)
        {
            if(!Product.list.get(i).collected)
            Product.list.get(i).expirationTime--;
        }

        boolean b = true;
        while(b)
        {
            b = false;

            for(int i=0; i<Product.list.size(); i++)
            {
                if(Product.list.get(i).expirationTime<=0)
                {
                    Logger.write('i',Product.list.get(i).type+" got expired");
                    Product.list.remove(i);//proposed
                    b = true;
                    break;///????
                }
            }
        }
    }

}
