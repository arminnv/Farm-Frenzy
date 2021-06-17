import java.util.Scanner;

public class Game {

    static int coins = 4000;
    static Mission mission;

    static void run(Mission mission)
    {
        Game.mission = mission;
        Task.add(mission);
        Scanner sc = new Scanner(System.in);

        while (true)
        {
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

            Domestic.buy("chiCkeN");
            Domestic.buy("BuFfalo");
            Cat.buy();
            Hound.buy();
            //Wild.add("Tiger");
            //Wild.add("BeAR");
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
            break;
        }
    }
}
