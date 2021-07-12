public class Game {

    private static int coins = 4000;
    public static MyClock myClock=new MyClock();
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
        Menu.game=true;
        Canvas canvas = new Canvas();
        canvas.setFrame();
        System.out.println(Menu.game);
        myClock.start();
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
        //Menu.getMainMenuInstance().setVisible(true);
    }

    public static void addCoins(int coins) {
        Game.coins += coins;
        Task.claim("Coins");
    }

    public static int getCoins() {
        return coins;
    }
}