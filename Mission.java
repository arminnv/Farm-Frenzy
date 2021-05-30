import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;

public class Mission {
    static final File file=new File("missions.json");
    static int levelNums;
    static ArrayList<Mission> missionArrayList=new ArrayList<>();
    int coins;
    int Time;
    int bonus;
    Mission (int coins, int bonus){
        Time=0;
        this.coins=coins;
        this.bonus=bonus;
        missionArrayList.add(this);
    }


}
