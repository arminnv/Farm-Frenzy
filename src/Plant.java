import java.lang.reflect.Array;
import java.util.ArrayList;

public class Plant {

    static int[][] num = new int[6][6];


    static void plant(int x, int y)
    {
        num[x-1][y-1]++;
        Logger.write('i',"planted successfully");
    }
}
