public class Game {

    private static int coins = 4000;
    static Mission mission;
    static void run(Mission mission)
    {
        System.out.println("level "+mission.level+" started");
        Game.mission = mission;
        Task.add(mission);
            Domestic.buy("chicken");
            Domestic.buy("chicken");
            Domestic.buy("chicken");
            Domestic.buy("chicken");
            Cat.buy();
            Hound.buy();
            Wild.add("lion");
            Well.getInstance().water(3,3);
            Well.getInstance().water(3,3);
            Well.getInstance().water(3,3);
            Well.getInstance().water(3,3);
        while (!Task.check(mission.level)) {
            //InputProcessor.process();
            Time.update();
            try {
            Thread.sleep(10);

            }
            catch (Exception e)
            {

            }





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