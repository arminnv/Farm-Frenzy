import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Animal {
    static HashMap<String,Integer> animalIntegerHashMap=new HashMap<>();
    static HashMap<String,Integer> animalNumber=new HashMap<>();
    static ArrayList<Animal> animals=new ArrayList<>();

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
    public void randomStep(){
        int d = randomDirection();
        if((d==0)&&(x+1<=6))
            x++;
        else if((d==1)&&(y+1<=6))
            y++;
        else if((d==2)&&(x-1>=1))
            x--;
        else if((d==3)&&(y-1>=1))
            y--;
    }
    public void addToMap(){
        if (animalIntegerHashMap.containsKey(this.type)){
            animalIntegerHashMap.put(this.type,animalIntegerHashMap.get(this.type)+1);
            animalNumber.put(this.type,animalNumber.get(this.type)+1);
        }
        else {
            animalNumber.put(this.type,1);
            animalIntegerHashMap.put(this.type,1);
        }
    }
    public static void removeFromMap(String type){
        if (animalNumber.containsKey(type)){
            animalNumber.put(type,animalNumber.get(type)-1);
            if (animalNumber.get(type)==0)
                animalNumber.remove(type);
            for (int i=0;i<animals.size();i++) {
                Animal animal=animals.get(i);
                if (animal.type.equals(type)){
                    animals.remove(animal);
                    break;
                }
            }
        }
    }
    abstract void walk();
}