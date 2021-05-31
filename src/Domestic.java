import java.util.ArrayList;
import java.util.HashMap;

public class Domestic extends Animal{

    static ArrayList<Domestic> list = new ArrayList<>();
    static HashMap<String, String> domesticProduct = new HashMap<>();

    String type;
    String productType;
    int health = 100;
    int productionTime;
    int lifeReduction = 10;
    boolean hungry = false;

    Domestic()
    {}


    void eat()
    {}

    void produce()
    {}

    void walk()
    {}

    void die()
    {}

}
