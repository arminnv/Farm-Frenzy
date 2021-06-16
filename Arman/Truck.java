import java.util.HashMap;

public class Truck {
    private static Truck t;
    public static final int MAX_CAPACITY=10;
    int capacity;
    boolean loaded;
    HashMap<Animal,Integer> animalIntegerHashMap=new HashMap<>();
    HashMap<Product,Integer> productIntegerHashMap=new HashMap<>();
    Truck(){
        capacity=0;
        loaded=false;
    }
    public static Truck getInstance() {
        if (t==null)
            t=new Truck();
        return t;
    }
    public void load(Product product){
        if (Warehouse.getInstance())
    }
    public void load(Animal animal){
        if (Animal.animalIntegerHashMap.containsKey(animal.type))
    }
    public void unload(){
        animalIntegerHashMap.clear();
        productIntegerHashMap.clear();
    }
    public void confirm(){


    }
}
