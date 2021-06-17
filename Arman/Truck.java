import java.util.HashMap;

public class Truck {
    private static Truck t;
    public static final int MAX_CAPACITY=15;
    int capacity;
    boolean loaded;
    HashMap<String,Integer> IntegerHashMap=new HashMap<>();
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
        HashMap<String,Integer> map=Warehouse.getInstance().productIntegerHashMap;
        if (map.containsKey(product.type)){
            if ( (map.get(product.type)>0)&&(capacity+product.space<=MAX_CAPACITY)) {
                capacity+=product.space;
                IntegerHashMap.put(product.type, map.get(product.type)+1);
            }
        }
    }
    /*
    public void load(Animal animal){
        HashMap<String,Integer> map=Animal.animalIntegerHashMap;
        if (map.containsKey(animal.type)){
            if ( (map.get(animal.type)>0)&&(capacity+animal.space<=MAX_CAPACITY)) {
                capacity+=animal.space;
                IntegerHashMap.put(animal.type, map.get(animal.type)+1);
            }
        }
    }
     */
    public void unload(){
        IntegerHashMap.clear();
        capacity=0;
    }
    public void confirm(){


    }
}
