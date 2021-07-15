import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private static int coins = 4000;
    public static MyClock myClock=new MyClock();
    static Mission mission;
    static void MyStart(Mission mission){
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
        canvas.frame.setBackground(Color.green);
        myClock=new MyClock();
        canvas.setFrame();
    }
    static void run(Mission mission) {

        MyStart(mission);
        //GFrame gframe=new GFrame();
        //gframe.setFrame(canvas);
        System.out.println("setframe");
        //System.out.println(Menu.game);
        //for game clock start

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(myClock, 0, 5, TimeUnit.MILLISECONDS);
        //for game clock end
        //SwingUtilities.invokeLater();
        /*
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TODO
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */
        while (!Task.check(mission.level)) {
            //InputProcessor.process();
            Time.update();

            try {
            Thread.sleep(30);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        Main.ifRun=false;
        Menu.getMainMenuInstance().setVisible(true);
    }

    public static void addCoins(int coins) {
        Game.coins += coins;
        Task.claim("Coins");
    }

    public static int getCoins() {
        return coins;
    }
}