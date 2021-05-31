import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        Plant.plant(sc.nextInt(), sc.nextInt());
        //Plant.plant(sc.nextInt(), sc.nextInt());
        Output.show();
    }
}
