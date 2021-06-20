import java.util.HashMap;
import java.util.Random;

public abstract class Animal {
    static HashMap<String,Integer> animalIDNumHashMap =new HashMap<>();//non wild
    static HashMap<String,Integer> animalCount =new HashMap<>();//non wild

    String type = "";
    int number;
    int x;
    int y;
    int step;
    int price;
    int space;
    static Random rand = new Random();
    static int random()
    {
        return rand.nextInt(6) + 1;
    }

    static int randomDirection()
    {
        return rand.nextInt(4);
    }
    public void addToMap(){//important note: add chickens 1-3 then remove them. next chicken ID:1
        if (animalCount.containsKey(this.type)){
            animalIDNumHashMap.put(this.type, animalIDNumHashMap.get(this.type)+1);
            animalCount.put(this.type, animalCount.get(this.type)+1);
        }
        else {
            animalCount.put(this.type,1);
            animalIDNumHashMap.put(this.type,1);
        }
    }
    public static void removeFromMap(String type){
        if (animalCount.containsKey(type)){
            animalCount.put(type, animalCount.get(type)-1);
            if (animalCount.get(type)==0)
                animalCount.remove(type);
        }
    }
    abstract void walk();
    abstract void kill();
}
