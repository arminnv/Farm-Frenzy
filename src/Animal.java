import java.util.Random;

public abstract class Animal {

    String type = "";
    int x;
    int y;
    int step;
    int price;

    static int random()
    {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
}
