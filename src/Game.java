public class Game {

    static int coins = 4000;
    static Mission mission;
    static void run(Mission mission)
    {
        System.out.println("level "+mission.level+" started");
        Game.mission = mission;
        Task.add(mission);
        while (!Task.check(mission.level)) {
            InputProcessor.process();
        }
        Menu.main(User.current);
    }

    public static void addCoins(int coins) {
        Game.coins += coins;
        if(coins > 0)
            Task.claim(coins);
    }

    /*public static int getCoins() {
        return coins;
    }*/
}