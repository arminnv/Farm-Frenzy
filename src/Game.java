public class Game {

    private static int coins = 4000;
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
        Task.claim("Coins");
    }

    public static int getCoins() {
        return coins;
    }
}