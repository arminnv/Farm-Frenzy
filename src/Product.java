import java.util.*;

public class Product {
    public static final Set<String> nameList=Set.of("Egg", "Feather", "Milk", "Flour", "Fabric", "Packet Milk", "Bread", "Shirt", "Ice Cream", "Lion", "Bear", "Tiger");
    static ArrayList<Product> list = new ArrayList<>();
    String type;
    String previousType;//possibly be omitted
    int price;
    int expirationTime = -3;
    int x;
    int y;
    boolean collected = false;
    int space;
    Product(String name)
    {
        type = name;
        if(name.equals("Egg"))
        {
            previousType = "";
            price = 15;
            expirationTime = 4;
            space = 1;
        }
        else if(name.equals("Feather"))
        {
            previousType = "";
            price = 20;
            expirationTime = 4;
            space = 1;
        }
        else if(name.equals("Milk"))
        {
            previousType = "";
            price = 25;
            expirationTime = 4;
            space = 1;
        }
        else if(name.equals("Flour"))
        {
            previousType = "Egg";
            price = 40;
            expirationTime = 5;
            space = 2;
        }
        else if(name.equals("ّFabric"))
        {
            previousType = "Feather";
            price = 50;
            expirationTime = 5;
            space = 2;
        }
        else if(name.equals("Packet Milk"))
        {
            previousType = "Milk";
            price = 60;
            expirationTime = 5;
            space = 2;
        }
        else if(name.equals("Bread"))
        {
            previousType = "Flour";
            price = 80;
            expirationTime = 6;
            space = 4;
        }
        else if(name.equals("Shirt"))
        {
            previousType = "Fabric";
            price = 100;
            expirationTime = 6;
            space = 4;
        }
        else if(name.equals("Ice Cream"))
        {
            previousType = "Packet Milk";
            price = 120;
            expirationTime = 6;
            space = 4;
        }
        else if(name.equals("Lion"))
        {
            previousType = "";
            price = 300;
            expirationTime = 5;
            space = 15;
        }
        else if(name.equals("Bear"))
        {
            previousType = "";
            price = 400;
            expirationTime = 5;
            space = 15;
        }
        else if(name.equals("Tiger"))
        {
            previousType = "";
            price = 500;
            expirationTime = 5;
            space = 15;
        }
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
            if(!Product.list.get(i).collected && Product.list.get(i).expirationTime!=-3)
                Product.list.get(i).expirationTime--;
        }

        boolean b = true;
        while(b)
        {
            b = false;

            for(int i=0; i<Product.list.size(); i++)
            {
                if(Product.list.get(i).expirationTime<=0 && Product.list.get(i).expirationTime!= -3)
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