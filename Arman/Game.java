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
        Animal.deleteAnimals();
        Factory.deleteFactories();
        Truck.deleteTruck();
        Plant.deletePlants();
        Well.deleteWell();
        Warehouse.deleteWarehouse();
        coins=4000;

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
        myClock=new MyClock();//important
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
        while ( (!Task.check(mission.level) )&&Main.ifRun) {
            //InputProcessor.process();
            Time.update();

            try {
            Thread.sleep(20);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!Main.ifRun){//if not restart or map selection or logout
            Task.clear();
            if (Main.ifRestart){
                Main.ifRestart=false;
                Main.ifRun=true;

            }
            else if (Main.ifMap){
                Main.ifMap=false;
                Menu.getMainMenuInstance().setVisible(true);
                Canvas.frame.setVisible(false);
            }
            else if (Main.ifLogout){
                Main.ifLogout=false;
                Canvas.frame.setVisible(false);
                Menu.getLoginMenuInstance().setVisible(true);
            }

        }
        else {
            Main.ifRun = false;
            Menu.getMainMenuInstance().setVisible(true);
            Canvas.frame.setVisible(false);
        }
    }

    public static void addCoins(int coins) {
        Game.coins += coins;
        Task.claim("Coins");
    }

    public static int getCoins() {
        return coins;
    }
}