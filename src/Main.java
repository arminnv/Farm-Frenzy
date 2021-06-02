import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Handler;

public class Main {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        Mission.levels = 5;
        int level = 1;
        int coins = 4000;
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Coins", 60000));
        tasks.add(new Task("Bread", 4));
        HashMap<Integer, String> wilds = new HashMap<>();
        wilds.put(2, "Tiger");
        wilds.put(4, "Bear");
        int maxTime = 500;
        HashMap<Integer, Integer> bonus = new HashMap<>();

        Mission mission1 = new Mission(level, coins, tasks, wilds, maxTime, bonus);
        Mission.list.add(mission1);
        Mission.save();
        Mission.load();
        Task.add(mission1);
        System.out.println(Mission.list.get(0).coins);

        Plant.plant(1, 3);
        Plant.plant(4, 5);
        Plant.plant(6, 2);
        Plant.plant(6, 2);
        Plant.plant(6, 2);
        Plant.plant(6, 2);
        Plant.plant(6, 2);
        Plant.plant(1, 3);
        Plant.plant(4, 5);
        Plant.plant(1, 3);
        Plant.plant(4, 5);
        /*for(int j=0; j<6; j++)
        for(int i=0; i<6; i++)
            Plant.num[i][j]= 4;*/
        Domestic.buy("chiCkeN");
        Domestic.buy("BuFfalo");
        Cat.buy();
        Hound.buy();
        Wild.add("Tiger");
        Wild.add("BeAR");
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();
        Time.turn(1);
        Output.show();

        Product.pickup(sc.nextInt(), sc.nextInt());
        int x= sc.nextInt();
        int y = sc.nextInt();
        Wild.cage(x, y);
        Wild.cage(x, y);
        //Wild.cage(x, y);
        //Wild.cage(x, y);
        //Wild.cage(x, y);
        Output.show();
        Time.turn(1);
        Output.show();
        //System.out.println(G4ame.coins);
    }
}
