import java.lang.reflect.Array;
import java.util.ArrayList;

public class Plant {

    static int[][] num = new int[6][6];


    static void plant(int x, int y)
    {
        num[x-1][y-1]++;
        Logger.write('i',"planted successfully");
    }
    public static void warn(){
        int a=0;
        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++){
                a+=num[i][j];
            }
        }
        if (a==0)
            System.out.println("Warning: no grass");
    }
}