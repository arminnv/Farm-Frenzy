import java.util.HashMap;
import java.util.Random;

public abstract class Animal {
    static HashMap<String,Integer> animalIDNumHashMap =new HashMap<>();//non wild
    static HashMap<String,Integer> animalCount =new HashMap<>();//non wild

    String type = "";
    int number;
    double x;
    double y;
    double dx;
    int step;
    int price;
    int space;
    int state;
    int w;
    int h;
    static Random rand = new Random();
    Animal(){}
    public Animal(String type, int price, int step, int space){
        this.x=random();
        this.y=random();
        this.type=type;
        this.space=space;
        this.step=step;
        this.price=price;
        this.dx = (double) (step) * Time.dt;
    }
    static double random()
    {
        return (double)(rand.nextInt(6)) + 0.5;
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

    boolean intRange(double X, double Y)
    {
        if( Math.abs(x-X)<1 && Math.abs(y-Y)<1 )
            return true;
        else
            return false;
    }

    int xScale()
    {
        int x = 0;
        return x;
    }

    int yScale()
    {
        int y = 0;
        return y;
    }

    abstract void walk();
    abstract void kill();
}