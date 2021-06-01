import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        //Plant.plant(sc.nextInt(), sc.nextInt());
        //Plant.plant(sc.nextInt(), sc.nextInt());
        for(int i=0; i<6; i++)
            Plant.num[i][1]= 4;
        Domestic.buy("chiCkeN");
        Domestic.buy("BuFfalo");
        Cat.buy();
        Hound.buy();
        Wild.add("Tiger");
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

        //System.out.println(Game.coins);
    }
}
