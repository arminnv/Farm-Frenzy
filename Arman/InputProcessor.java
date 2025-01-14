import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class InputProcessor {
    public static String buy="(?i)buy \\w+";
    public static String pickup="(?i)pickup \\d+ \\d+";
    public static String well="(?i)well";
    public static String plant="(?i)plant \\d+ \\d+";
    public static String work="(?i)work [\\w- ]+";
    public static String workNumber="(?i)work [\\w- ]+ \\d+";
    public static String cage="(?i)cage \\d+ \\d+";
    public static String turn="(?i)turn \\d+";
    public static String truckLoad="(?i)truck load \\w+";
    public static String truckUnLoad="(?i)truck unload \\w+";
    public static String truckGo="(?i)truck go";
    public static String wareHouseShow="(?i)warehouse";
    public static String inquiry="(?i)inquiry";
    public static String exit="(?i)exit";
    public static String buildFactory="(?i)build [\\w- ]+";
    public static String upgradeFactory="(?i)upgrade [\\w- ].+";

    public static void process(){
        Scanner scanner=new Scanner(System.in);
        String input=scanner.nextLine();
        String[] a=input.split(" ");
        if (input.matches(exit))
            return;
        if (input.matches(truckLoad))
            Truck.getInstance().TruckLoad(a[2].toLowerCase(Locale.ROOT));
        else if (input.matches(truckUnLoad))
            Truck.getInstance().TruckUnload(a[2].toLowerCase(Locale.ROOT));
        else if (input.matches(truckGo))
            Truck.getInstance().confirm();
        else if (input.matches(well)){
            Well.getInstance().fill();
        }
        else if (input.matches(work)){
            input=input.replaceAll("(?i)work ","");
            Factory.produce(input.toLowerCase(Locale.ROOT),1);
        }
        else if (input.matches(workNumber)){
            input=input.replaceAll("(?i)work ","");
            a=input.split("\\D");
            System.out.println(Arrays.toString(a));
            Factory.produce(a[1].toLowerCase(Locale.ROOT),Integer.parseInt(a[0]));
        }
        else if (input.matches(cage)){
            Wild.cage(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
        }
        else if (input.matches(pickup)){
            Product.pickup(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
        }
        else if (input.matches(buy)){
            Domestic.buy(a[1]);
        }
        else if (input.matches(plant)){
            Well.getInstance().water(Integer.parseInt(a[1]),Integer.parseInt(a[2]));
        }
        else if (input.matches(wareHouseShow)){
            Output.showWarehouse();
        }
        else if (input.matches(inquiry))
            Output.show();
        else if (input.matches(turn)){
            Time.turn(Integer.parseInt(a[1]));
        }
        else if (input.matches(buildFactory)){
            input=input.replaceAll("(?i)build ","");
            Factory.build(input.toLowerCase(Locale.ROOT));
        }
        else if (input.matches(upgradeFactory)){
            input=input.replaceAll("(?i)upgrade ","");
            Factory.upgrade(input.toLowerCase(Locale.ROOT));
        }
        else {
            System.out.println("Invalid command. Enter command again");
        }
    }
}