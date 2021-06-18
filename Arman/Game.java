import java.util.Scanner;

public class Game {

    static int coins = 4000;
    static Mission mission;

    static void run(Mission mission)
    {
        Game.mission = mission;
        Task.add(mission);
        Factory.Initialise();
        while (true)
        {
            InputProcessor.process();
            if(Task.check(mission.level))
                break;
        }
        Menu.main(User.current);
    }
}