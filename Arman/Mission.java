import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Mission {

    static int levels;
    static ArrayList<Mission> list = new ArrayList<>();

    int level;
    int coins;
    ArrayList<Task> tasks = new ArrayList<>();
    HashMap<Integer, String> wilds = new HashMap<>();
    int maxTime;
    HashMap<Integer, Integer> bonus = new HashMap<>();

    Mission(int Level, int Coins, ArrayList<Task> Tasks, HashMap<Integer, String> Wilds, int MaxTime, HashMap<Integer, Integer> Bonus)
    {
        level = Level;
        coins = Coins;
        tasks = Tasks;
        wilds = Wilds;
        maxTime = MaxTime;
        bonus = Bonus;
    }

    static void load()
    {
        try
        {
            File file = new File("missions.txt");
            Scanner reader = new Scanner(file);
            String str = "";
            reader.next();
            Mission.levels = reader.nextInt();
            reader.nextLine();

            while(reader.hasNextLine())
            {
                str += reader.nextLine();
                if(str.charAt(str.length()-1) == '*')
                {
                    str = str.substring(0, str.length()-1);
                    Gson gson = new Gson();
                    Mission mission = gson.fromJson(str, Mission.class);
                    Mission.list.add(mission);
                    break;
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("error");
        }
    }

    static void save()
    {
        try
        {
            FileWriter writer = new FileWriter("missions.txt");
            writer.write("levels: " + Mission.levels +"\n");

            for(int i=0; i<Mission.list.size(); i++)
            {
                Mission mission = Mission.list.get(i);
                String json = new Gson().toJson(mission);
                writer.write(json + "*");
            }
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
    }


    static void write()
    {
        Mission.levels = 5;
        int level = 1;
        int coins = 4000;
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Coins", 60000));
        tasks.add(new Task("Bread", 4));
        tasks.add(new Task("Milk", 5));
        tasks.add(new Task("Egg", 6));
        HashMap<Integer, String> wilds = new HashMap<>();
        wilds.put(2, "Tiger");
        wilds.put(1, "Bear");
        int maxTime = 500;
        HashMap<Integer, Integer> bonus = new HashMap<>();

        Mission mission1 = new Mission(level, coins, tasks, wilds, maxTime, bonus);
        Mission.list.add(mission1);
        Mission.save();
        Mission.list.clear();
    }
}
