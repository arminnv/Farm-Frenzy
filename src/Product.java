import java.util.ArrayList;

public class Product {

    static ArrayList<Product> list = new ArrayList<>();

    String type;
    String previousType;
    int price;
    int expirationTime;
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
            if(Product.list.get(i).x == x && Product.list.get(i).y == y)
                Product.list.get(i).collected = true;
        }
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
                    Product.list.remove(i);
                    b = true;
                    break;
                }
            }
        }
    }

}
