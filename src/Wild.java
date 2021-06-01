import java.util.ArrayList;

public class Wild extends Animal{

    static ArrayList<Wild> list = new ArrayList<>();

    int cages;
    int leftCages;

    Wild(String name)
    {
        name = name.toLowerCase();
        if(name.equals("lion"))
        {
            type = "Lion";
            price = 300;
            step = 1;
            cages = 3;
            leftCages = 3;
            x = random();
            y = random();
        }

        else if(name.equals("bear"))
        {
            type = "Bear";
            price = 400;
            step = 1;
            cages = 4;
            leftCages = 4;
            x = random();
            y = random();
        }

        else if(name.equals("tiger"))
        {
            type = "Tiger";
            price = 500;
            step = 2;
            cages = 4;
            leftCages = 4;
            x = random();
            y = random();
        }
    }


    void walk()
    {}

    void kill()
    {}
}
