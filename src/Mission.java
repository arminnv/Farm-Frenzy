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
    int reward;
    ArrayList<Task> tasks = new ArrayList<>();
    HashMap<Integer, String> wilds = new HashMap<>();
    int maxTime;
    HashMap<Integer, Integer> TimeBonus = new HashMap<>();

    Mission(int Level, int Coins, ArrayList<Task> Tasks, HashMap<Integer, String> Wilds, int MaxTime, HashMap<Integer, Integer> TimeBonus, int reward)
    {
        this.reward=reward;
        level = Level;
        coins = Coins;
        tasks = Tasks;
        wilds = Wilds;
        maxTime = MaxTime;
        this.TimeBonus = TimeBonus;
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
                    str="";
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
                writer.write(json + "*\n");
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
        int level;
        int coins;
        int maxTime;
        int reward;

        //mission 1
        level = 1;
        coins = 4000;
        reward=10;
        ArrayList<Task> tasks1 = new ArrayList<>();
        HashMap<Integer, Integer> bonus1 = new HashMap<>();
        HashMap<Integer, String> wilds1 = new HashMap<>();
        bonus1.put(10,200);
        bonus1.put(20,20);
        bonus1.put(30,0);
        tasks1.add(new Task("Coins", 600));
        tasks1.add(new Task("Bread", 4));
        tasks1.add(new Task("Milk", 5));
        tasks1.add(new Task("Egg", 6));
        wilds1.put(2, "Tiger");
        wilds1.put(1, "Bear");
        maxTime = 500;
        Mission mission1 = new Mission(level, coins, tasks1, wilds1, maxTime, bonus1,reward);
        Mission.list.add(mission1);


        //mission 2
        level = 2;
        coins = 4000;
        ArrayList<Task> tasks2 = new ArrayList<>();
        HashMap<Integer, Integer> bonus2 = new HashMap<>();
        HashMap<Integer, String> wilds2 = new HashMap<>();
        tasks2.add(new Task("Coins", 600));
        tasks2.add(new Task("Bread", 4));
        tasks2.add(new Task("Milk", 5));
        tasks2.add(new Task("Egg", 6));
        wilds2.put(2, "Tiger");
        wilds2.put(1, "Bear");
        maxTime = 500;
        Mission mission2 = new Mission(level, coins, tasks2, wilds2, maxTime, bonus2,reward);
        Mission.list.add(mission2);
        Mission.save();
        Mission.list.clear();
        //mission 3
        level=3;
        coins=4000;
        ArrayList<Task> tasks3=new ArrayList<>();
        HashMap<Integer, Integer> bonus3=new HashMap<>();
        HashMap<Integer,String> wilds3=new HashMap<>();
        tasks3.add(new Task("Fabric", 1));
        wilds3.put(1,"Lion");
        Mission mission3=new Mission(level,coins, tasks3,wilds3,maxTime,bonus3,reward);
    }
}