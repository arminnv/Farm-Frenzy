import java.util.HashMap;
import java.util.Random;

public abstract class Animal {
    static HashMap<String,Integer> animalIntegerHashMap=new HashMap<>();

    String type = "";
    int x;
    int y;
    int step;
    int price;
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
        }
        else {
            animalIntegerHashMap.put(this.type,1);
        }
    }
}
