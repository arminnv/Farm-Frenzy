import com.google.gson.Gson;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public abstract class Animal {
    static HashMap<String,Integer> animalIDNumHashMap =new HashMap<>();//non wild
    static HashMap<String,Integer> animalCount =new HashMap<>();//non wild

    String type = "";
    int number;
    int x;
    int y;
    int step;
    int price;
    int space;
    static Random rand = new Random();
    static int random()
    {
        return rand.nextInt(6) + 1;
    }

    static int randomDirection()
    {
        return rand.nextInt(4);
    }
    public void addToMap(){//important note: add chickens 1-3 then remove them. next chicken ID:1
        if (animalCount.containsKey(this.type)){
            animalIDNumHashMap.put(this.type, animalIDNumHashMap.get(this.type)+1);
            animalCount.put(this.type, animalCount.get(this.type)+1);
        }
        else {
            animalCount.put(this.type,1);
            animalIDNumHashMap.put(this.type,1);
        }
    }
    public static void removeFromMap(String type){
        if (animalCount.containsKey(type)){
            animalCount.put(type, animalCount.get(type)-1);
            if (animalCount.get(type)==0)
                animalCount.remove(type);
        }
    }
    abstract void walk();
    abstract void kill();
}
public class Bakery extends Factory{
    Bakery(boolean addToMap){
        super("bakery","flour","bread",250,5,addToMap);
    }
}
import java.util.ArrayList;

public class Cat extends Animal{

    static ArrayList<Cat> list = new ArrayList<>();

    Cat(boolean addToMap)
    {
        type = "cat";
        price = 100;
        step = 1;
        x = random();
        y = random();
        space=1;

        if (addToMap) {
            this.addToMap();
            number = animalIDNumHashMap.get("cat");
        }
    }

    static void buy()
    {
        Cat newCat = new Cat(false);
        if(newCat.price <= Game.getCoins())
        {
            newCat=new Cat(true);
            Game.addCoins(-newCat.price);
            Cat.list.add(newCat);
            Task.claim("cat");
            Logger.write('i', "cat has been bought");
        }
        else
        {
            System.out.println("not enough coins");
            Logger.write('e', "not enough coins");
        }
    }

    void kill()
    {
        Cat.list.remove(this);
        removeFromMap(this.type);
        Logger.write('i',"cat got killed");
    }

    void walk()
    {
        if(this.find() == null) {
            int d = randomDirection();
            if((d==0)&&(x+step<=6))
                x += step;
            else if((d==1)&&(y+step<=6))
                y += step;
            else if((d==2)&&(x-step>=1))
                x -= step;
            else if((d==3)&&(y-step>=1))
                y -= step;
        }
        else
        {
            Product target = this.find();
            if(target.x > x)
                x++;
            else if(target.x < x)
                x--;
            else if(target.y > y)
                y++;
            else if(target.y < y)
                y--;
        }
    }

    void collect()
    {
        for (int i=0; i<Product.list.size(); i++)
        {
            Product product = Product.list.get(i);
            if(!product.collected)
            {
                if( (product.x == x && product.y == y) && ( Warehouse.getInstance().add(product) ) ) {
                    product.collected = true;///
                    Product.list.remove(product);//proposed
                    Task.claim(product.type);
                    Logger.write('i',"cat collected "+product.type);
                }
            }
        }
    }

    Product find()
    {
        int min = 20;
        Product closest = null;
        for (int i=0; i<Product.list.size(); i++)
        {
            Product product = Product.list.get(i);//cats collect caged wild animals
            if(!product.collected)
            {
                if(Math.abs(product.x-x) + Math.abs(product.y-y) < min)
                {
                    closest = product;
                    min = Math.abs(product.x-x) + Math.abs(product.y-y);
                }
            }
        }
        return closest;
    }
}
import java.util.ArrayList;
        import java.util.Set;

public class Domestic extends Animal{

    static ArrayList<Domestic> list = new ArrayList<>();
    public static final Set<String> nameList=Set.of("chicken","turkey","buffalo");
    String productType;
    int health = 100;
    int productionTime;
    int timePassed = 0;
    int lifeReduction = 10;

    Domestic(String name, boolean addToMap)
    {
        if(name.equals("chicken"))
        {
            type = "chicken";
            productionTime = 2;
            productType = "egg";
            price = 100;
            step = 1;
            space = 1;
            x = random();
            y = random();
            if (addToMap) {
                this.addToMap();
                number = animalIDNumHashMap.get("chicken");
            }
        }

        else if(name.equals("turkey"))
        {
            type = "turkey";
            productionTime = 3;
            productType = "feather";
            price = 200;
            step = 1;
            space = 1;
            x = random();
            y = random();
            if (addToMap) {
                this.addToMap();
                number = animalIDNumHashMap.get("turkey");
            }
        }

        else if(name.equals("buffalo"))
        {
            type = "buffalo";
            productionTime = 5;
            productType = "milk";
            price = 400;
            step = 1;
            space = 1;
            x = random();
            y = random();
            if (addToMap) {
                this.addToMap();
                number = animalIDNumHashMap.get("buffalo");
            }
        }
    }

    static void buy(String name)
    {
        name = name.toLowerCase();

        if(name.equals("cat"))
        {
            Cat.buy();
            return;
        }
        else if(name.equals("hound"))
        {
            Hound.buy();
            return;
        }

        Domestic newDomestic = new Domestic(name,true);
        if(newDomestic.type.equals(""))
        {
            System.out.println("animal type not found");
            Logger.write('e',"animal type not found");
            return;
        }

        if(newDomestic.price <= Game.getCoins())
        {
            Game.addCoins(-newDomestic.price);
            Domestic.list.add(newDomestic);
            Task.claim(newDomestic.type);
            Logger.write('i',newDomestic.type + " has been bought");
            System.out.println(newDomestic.type + " has been bought");
        }
        else
        {
            System.out.println("not enough coins");
            Logger.write('e',"not enough coins");
        }
    }


    void eat()
    {
        if(health>50)
            return;
        if(Plant.num[x-1][y-1]>0)
        {
            Plant.num[x-1][y-1]--;
            health = 100;
            Logger.write('i', type+ " ate");
        }
    }

    void produce()
    {
        timePassed++;
        if(timePassed >= productionTime)
        {
            timePassed = 0;
            Product newProduct = new Product(productType);
            newProduct.x = x;
            newProduct.y = y;
            Product.list.add(newProduct);
            Logger.write('i',type + " produced " + productType);
        }
    }

    void kill()
    {
        Domestic.list.remove(this);
        removeFromMap(this.type);
        Logger.write('i', type + " died");
    }

    void walk()
    {
        int[] closest = {0,0};
        find(closest);
        if(health > 50 || closest[0] == 0)
        {
            int d = randomDirection();
            if(d==0)
                x += step;
            else if(d==1)
                y += step;
            else if(d==2)
                x -= step;
            else if(d==3)
                y -= step;

            if(x>6)
                x=6;
            if(x<1)
                x=1;
            if(y>6)
                y=6;
            if(y<1)
                y=1;
        }
        else
        {
            int x0 = closest[0];
            int y0 = closest[1];
            if(x0 > x)
                x++;
            else if(x0 < x)
                x--;
            else if(y0 > y)
                y++;
            else if(y0 < y)
                y--;
        }

    }

    static void reduce()
    {
        for(int i=0; i<Domestic.list.size(); i++) {
            Domestic.list.get(i).health -= Domestic.list.get(i).lifeReduction;
        }
        for(int i=0; i<Domestic.list.size(); i++) {
            if(Domestic.list.get(i).health<=0) {
                Domestic.list.remove(i);
                i--;
            }
        }

    }

    void find(int[] closest)
    {
        int min = 20;
        for (int i=0; i<6; i++)
        {
            for(int j=0; j<6; j++)
            {
                if(Plant.num[i][j] > 0)
                {
                    if(Math.abs(i+1-x) + Math.abs(j+1-y) < min)
                    {
                        closest[0] = i+1;
                        closest[1] = j+1;
                        min = Math.abs(i+1-x) + Math.abs(j+1-y);
                    }
                }
            }
        }
    }





}
import java.util.ArrayList;
        import java.util.Random;

public abstract class Factory {
    public static final String[] nameList={"mill","weaver","sewing factory","milk packaging factory","ice cream factory","bakery"};
    static ArrayList<Factory> factories=new ArrayList<>();
    static Random r=new Random();
    int level;
    int maxAllowedLevel;
    int buildingCost;
    int upgradeCost;
    int productionDefaultDuration;
    int productionDuration;
    String name;
    String validType;
    String outputType;
    int underProduction;
    protected Factory(String name, String validType,String outputType,int buildingCost, int productionDefaultDuration, boolean addToList){
        this.name=name;
        this.outputType=outputType;
        this.validType=validType;
        this.buildingCost=buildingCost;
        this.productionDefaultDuration=productionDefaultDuration;
        this.productionDuration=0;
        this.upgradeCost=buildingCost*3/2;
        underProduction=0;
        level=1;
        maxAllowedLevel=2;
        if (addToList)
            factories.add(this);
    }
    private static Factory createFactory(String name,boolean addToList){
        Factory factory=null;
        if (name.equals("mill"))
            factory=new Mill(addToList);
        else if (name.equals("weaver"))
            factory=new Weaver(addToList);
        else if (name.equals("sewing factory"))
            factory=new SewingFactory(addToList);
        else if (name.equals("milk packaging factory"))
            factory=new MilkPackagingFactory(addToList);
        else if (name.equals("ice cream factory"))
            factory=new IceCreamFactory(addToList);
        else if (name.equals("bakery"))
            factory=new Bakery(addToList);
        return factory;
    }
    private void produce(int number) {
        if ((number!=0)&&Warehouse.getInstance().inquiry(validType,number)){
            productionDuration= (int)Math.ceil(productionDefaultDuration*number*1.0/level);
            Warehouse.getInstance().remove(new Product(validType),number);
            underProduction=number;//Class.forName(outputType.getTypeName()).getDeclaredConstructor().newInstance()
            System.out.println("Product is getting produced");
            Logger.write('i',"Product is getting produced");
            return;
        }
        System.out.println("Product cannot be produced");
        Logger.write('e',"Product cannot be produced");
    }
    private void upgrade(){
        if ( (Game.getCoins()>upgradeCost)&&(level+1<=maxAllowedLevel) ){
            Game.addCoins(-upgradeCost);
            level++;
            Logger.write('i',"Factory "+name+" was upgraded to level "+level);
            System.out.println("Factory "+name+" was upgraded to level "+level);
            return;
        }
        System.out.println("Insufficient money or max level error");
        Logger.write('e',"Insufficient money or max level error");
    }
    public void update(){
        if (productionDuration>0){
            productionDuration--;
        }
        if (productionDuration==0){
            for(int i=0;i<underProduction;i++){
                Product product=new Product(outputType);
                product.x=r.nextInt(6)+1;
                product.y=r.nextInt(6)+1;
                Product.list.add(product);
                Logger.write('i',this.name+ " produced "+product.type);
            }
            underProduction=0;
        }
    }
    public static void upgrade(String name){
        for (Factory factory:factories){
            if (factory.name.equals(name)) {
                factory.upgrade();
                return;
            }
        }
        System.out.println("Factory name is invalid");
        Logger.write('e',"Factory name is invalid");
    }
    public static void produce(String factoryName,int number){
        for (Factory factory:factories){
            if (factory.name.equals(factoryName)) {
                factory.produce(number);
                return;
            }
        }
        System.out.println("Factory was not found");
        Logger.write('e',"Factory was not found");
    }
    public static void build(String name){
        for (String name1:nameList){
            if (name1.equals(name)){
                for (Factory factory:factories){
                    if (factory.name.equals(name)){
                        System.out.println("Factory already exists");
                        Logger.write('e',"Factory already exists");
                        return;
                    }
                }
                Factory f=createFactory(name,false);
                if (f!=null){//name is always valid so this is always true
                    if (f.buildingCost<=Game.getCoins()){
                        createFactory(name,true);
                        Game.addCoins(-f.buildingCost);
                        System.out.println("Factory was built successfully");
                        Logger.write('i',"Factory was built successfully");
                        return;
                    }
                }
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Factory name is invalid");
        Logger.write('e',"Factory name is invalid");
    }
}
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
import java.util.ArrayList;

public class Hound extends Animal{

    static ArrayList<Hound> list = new ArrayList<>();

    Hound()
    {
        type = "hound";
        price = 100;
        step = 1;
        x = random();
        y = random();
        space=1;
        this.addToMap();
        number= animalIDNumHashMap.get("hound");
    }

    static void buy()
    {
        Hound newHound = new Hound();
        if(newHound.price <= Game.getCoins())
        {
            Game.addCoins(-newHound.price);
            Hound.list.add(newHound);
            Task.claim("hound");
            Logger.write('i',"hound has been bought");
        }
        else
        {
            System.out.println("not enough coins");
            Logger.write('e',"not enough coins");
        }
    }

    void kill()
    {
        Hound.list.remove(this);
        removeFromMap(this.type);
        Logger.write('i',"hound got killed");
    }

    void walk()
    {
        int d = randomDirection();
        if(d==0)
            x += step;
        else if(d==1)
            y += step;
        else if(d==2)
            x -= step;
        else if(d==3)
            y -= step;

        if(x>6)
            x=6;
        if(x<1)
            x=1;
        if(y>6)
            y=6;
        if(y<1)
            y=1;
    }
}
public class IceCreamFactory extends Factory{
    IceCreamFactory(boolean addToMap){
        super("ice cream factory","packet milk","ice cream",550,7,addToMap);
    }
}
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
import java.io.File;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.time.LocalDateTime;
        import java.time.temporal.ChronoUnit;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.Scanner;

public class Logger {
    public static final File logFile=new File("log.txt");
    static void write(char ch, String st)
    {
        String s = "";
        String tag = "";
        String header="";

        if(ch  == 'i')
            tag = "[Info], ";
        else if(ch == 'e')
            tag = "[Error], ";
        else if(ch == 'a')
            tag = "[Alarm], ";
        else
            tag = "[Info], ";

        st = tag + getDate() + ", " + st;
        try
        {
            FileReader reader = new FileReader(logFile);
            Scanner sc = new Scanner(reader);
            header+=sc.nextLine()+"\n";
            sc.nextLine();
            sc.nextLine();
            while (sc.hasNextLine()) {
                s += sc.nextLine() + "\n";
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println(e.toString());
            LocalDateTime d=LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            header+= d.toLocalDate().toString()+" "+d.toLocalTime().toString()+"\n";
        }
        finally {
            header+="Latest change by :";
            if (User.current!=null)
                header+=User.current.userName+"\n";
            else
                header+="No user exists\n";
            LocalDateTime d=LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            header+= d.toLocalDate().toString()+" "+d.toLocalTime().toString()+"\n";
        }
        try
        {
            FileWriter writer = new FileWriter(logFile);
            if (!s.equals(""))
                writer.write(header +s + "\n" +  st);
            else
                writer.write(header+st);
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
    }

    static String getDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
public class Main {
    public static void main(String[] args)
    {
        Mission.write();
        User.load();
        Mission.load();
        Menu.enter();

    }
}
import java.util.Scanner;

public class Menu {
    static void enter()
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("log in | signup");
            String st = sc.next().toLowerCase();

            if(st.equals("log"))
            {
                sc.next();
                String name = sc.next();
                String pass = sc.next();
                User user = User.login(name, pass);
                if(user != null)
                {
                    User.current = user;
                    main(user);
                }
            }
            else if(st.equals("signup"))
            {
                String name = sc.next();
                String pass = sc.next();
                User user = User.signup(name, pass);
                if(user != null)
                {
                    main(user);
                }
            }
            else if (st.equals("exit"))
                break;
            else
                System.out.println("Invalid Command");
        }
    }

    static void main(User user)
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.println("start | log out | settings");
            String st = sc.next().toLowerCase();

            if(st.equals("start"))
            {
                int level = sc.nextInt();
                if(user.unlockedLevels >= level)
                {
                    Game.run(Mission.list.get(level-1));
                }
                else
                    System.out.println("level " + level + " is locked");
            }
            else if(st.equals("log"))
            {
                sc.next();
                enter();
                Logger.write('i',"log out successful");
                break;
            }
            else if(st.equals("settings")) {
                System.out.println("⚙ settings ⚙\n" +
                        "VOLUME   - ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒ +");
            }
        }
    }
}
public class MilkPackagingFactory extends Factory{
    MilkPackagingFactory(boolean addToMap){
        super("Milk packaging factory","Milk","Packet Milk",400,6, addToMap);
    }
}
public class Mill extends Factory{
    Mill(boolean addToMap){
        super("mill","egg","flour",150,4,addToMap);
    }
}
import com.google.gson.Gson;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Scanner;

public class Mission {

    static int levels;
    static ArrayList<Mission> list = new ArrayList<>();

    int level;
    int coins;
    int reward;
    ArrayList<Task> tasks = new ArrayList<>();
    HashMap<Integer, String> wilds = new HashMap<>();
    int maxTime;
    HashMap<Integer, Integer> TimeBonus = new HashMap<>();

    Mission(int Level, int Coins, ArrayList<Task> Tasks, HashMap<Integer, String> Wilds, int MaxTime, HashMap<Integer, Integer> TimeBonus, int reward)
    {
        this.reward=reward;
        level = Level;
        coins = Coins;
        tasks = Tasks;
        wilds = Wilds;
        maxTime = MaxTime;
        this.TimeBonus = TimeBonus;
    }

    static void load()
    {
        try
        {
            File file = new File("missions.txt");
            Scanner reader = new Scanner(file);
            String str = "";
            reader.next();
            Mission.levels = reader.nextInt();
            reader.nextLine();

            while(reader.hasNextLine())
            {
                str += reader.nextLine();
                if(str.charAt(str.length()-1) == '*')
                {
                    str = str.substring(0, str.length()-1);
                    Gson gson = new Gson();
                    Mission mission = gson.fromJson(str, Mission.class);
                    Mission.list.add(mission);
                    str="";
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("error");
        }
    }

    static void save()
    {
        try
        {
            FileWriter writer = new FileWriter("missions.txt");
            writer.write("levels: " + Mission.levels +"\n");

            for(int i=0; i<Mission.list.size(); i++)
            {
                Mission mission = Mission.list.get(i);
                String json = new Gson().toJson(mission);
                writer.write(json + "*\n");
            }
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
    }


    static void write()
    {
        int level;
        int coins;
        int maxTime;
        int reward;

        //mission 1
        level = 1;
        coins = 4000;
        reward=10;
        ArrayList<Task> tasks1 = new ArrayList<>();
        HashMap<Integer, Integer> bonus1 = new HashMap<>();
        HashMap<Integer, String> wilds1 = new HashMap<>();
        bonus1.put(10,200);
        bonus1.put(20,20);
        bonus1.put(30,0);
        //tasks1.add(new Task("Coins", 60000));
        //tasks1.add(new Task("Bread", 4));
        //tasks1.add(new Task("Milk", 5));
        tasks1.add(new Task("egg", 6));
        wilds1.put(2, "tiger");
        wilds1.put(1, "bear");
        maxTime = 500;
        Mission mission1 = new Mission(level, coins, tasks1, wilds1, maxTime, bonus1,reward);
        Mission.list.add(mission1);


        //mission 2
        level = 2;
        coins = 4000;
        ArrayList<Task> tasks2 = new ArrayList<>();
        HashMap<Integer, Integer> bonus2 = new HashMap<>();
        HashMap<Integer, String> wilds2 = new HashMap<>();
        tasks2.add(new Task("coins", 60000));
        tasks2.add(new Task("bread", 4));
        tasks2.add(new Task("milk", 5));
        tasks2.add(new Task("egg", 6));
        wilds2.put(2, "tiger");
        wilds2.put(1, "bear");
        maxTime = 500;
        bonus2=bonus1;
        Mission mission2 = new Mission(level, coins, tasks2, wilds2, maxTime, bonus2,reward);
        Mission.list.add(mission2);

        //mission 3
        level=3;
        coins=4000;
        ArrayList<Task> tasks3=new ArrayList<>();
        HashMap<Integer, Integer> bonus3=new HashMap<>();
        HashMap<Integer,String> wilds3=new HashMap<>();
        tasks3.add(new Task("cat",1));
        tasks3.add(new Task("hound",1));
        tasks3.add(new Task("lion",1));
        tasks3.add(new Task("fabric", 2));
        wilds3.put(1,"lion");
        bonus3=bonus1;
        Mission mission3=new Mission(level,coins, tasks3,wilds3,maxTime,bonus3,reward);
        Mission.list.add(mission3);

        //mission 4
        level=4;
        coins=10000;
        ArrayList<Task> tasks4=new ArrayList<>();
        HashMap<Integer,Integer> bonus4=new HashMap<>();
        HashMap<Integer,String> wilds4=new HashMap<>();
        tasks4.add(new Task("cat",1));
        tasks4.add(new Task("hound",1));
        tasks4.add(new Task("tiger",2));
        tasks4.add(new Task("ice cream",2));
        tasks4.add(new Task("bread",1));
        wilds4.put(3,"tiger");
        bonus4.put(20,200);
        bonus4.put(40,20);
        bonus4.put(60,0);
        Mission mission4=new Mission(level,coins,tasks4,wilds4,maxTime,bonus4,reward);
        Mission.list.add(mission4);
        //mission 5
        level=5;
        coins=5000;
        ArrayList<Task> tasks5=new ArrayList<>();
        HashMap<Integer,Integer> bonus5=new HashMap<>();
        HashMap<Integer,String> wilds5=new HashMap<>();
        wilds5.put(1,"bear");
        tasks5.add(new Task("flour",2));
        bonus5.put(6,10000);
        bonus5.put(7,0);
        Mission mission5=new Mission(level,coins,tasks5,wilds5,maxTime,bonus5,reward);


        Mission.save();
        Mission.list.clear();
    }
}
public class Output {

    static void show()
    {
        System.out.println("time : " + Time.time);
        System.out.println("coins: "+Game.getCoins());
        showLand();
        showAnimals();
        showProducts();
        showTasks();
        showWarehouse();
        System.out.println("--------------------------------------");
    }

    static void showLand()
    {
        System.out.println("+ - - - - - - +");
        for(int i=5; i>=0; i--)
        {
            System.out.print("| ");
            for (int j=0; j<6; j++)
                System.out.print(Plant.num[j][i] + " ");
            System.out.println("|");
        }
        System.out.println("+ - - - - - - +");
    }

    static void showAnimals()
    {
        for (int i=0; i<Domestic.list.size(); i++)
        {
            Domestic domestic = Domestic.list.get(i);
            System.out.println(domestic.type + " " + domestic.health + "% " + domestic.x + " " + domestic.y);
        }

        for (int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            System.out.println(wild.type + " left cages: "+ wild.leftCages + " " + wild.x + " " + wild.y);
        }

        for (int i=0; i<Cat.list.size(); i++)
        {
            Cat cat = Cat.list.get(i);
            System.out.println(cat.type + " " + cat.x + " " + cat.y);
        }

        for (int i=0; i<Hound.list.size(); i++)
        {
            Hound hound = Hound.list.get(i);
            System.out.println(hound.type + " " + hound.x + " " + hound.y);
        }
    }

    static void showProducts()
    {
        for (int i=0; i<Product.list.size(); i++) {
            Product product = Product.list.get(i);
            if(product.collected)
                continue;
            System.out.println(product.type + " " + product.x + " " + product.y);
        }
    }

    static void showTasks()
    {
        for (int i=0; i<Task.list.size(); i++) {
            Task task = Task.list.get(i);
            System.out.println(task.name + ": " + task.claimed + "/" + task.goal);
        }
    }
    static void showWarehouse(){
        Warehouse.getInstance().show();
    }

}
public class Plant {

    static int[][] num = new int[6][6];

    public static void warn(){
        int a=0;
        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++){
                a+=num[i][j];
            }
        }
        if (a==0) {
            System.out.println("Warning: no grass");
            Logger.write('a', "No grass");
        }
    }
}
import java.util.*;

public class Product {
    public static final Set<String> nameList=Set.of("egg", "feather", "milk", "flour", "fabric", "packet milk", "bread", "shirt", "ice cream", "lion", "bear", "tiger");
    static ArrayList<Product> list = new ArrayList<>();
    String type;
    int price;
    int expirationTime;
    int x;
    int y;
    boolean collected = false;
    int space;
    Product(String name)
    {
        type = name;
        if(name.equals("egg"))
        {
            price = 15;
            expirationTime = 4;
            space = 1;
        }
        else if(name.equals("feather"))
        {
            price = 20;
            expirationTime = 4;
            space = 1;
        }
        else if(name.equals("milk"))
        {
            price = 25;
            expirationTime = 4;
            space = 1;
        }
        else if(name.equals("flour"))
        {
            price = 40;
            expirationTime = 5;
            space = 2;
        }
        else if(name.equals("ّfabric"))
        {
            price = 50;
            expirationTime = 5;
            space = 2;
        }
        else if(name.equals("packet milk"))
        {
            price = 60;
            expirationTime = 5;
            space = 2;
        }
        else if(name.equals("bread"))
        {
            price = 80;
            expirationTime = 6;
            space = 4;
        }
        else if(name.equals("shirt"))
        {
            price = 100;
            expirationTime = 6;
            space = 4;
        }
        else if(name.equals("ice cream"))
        {
            price = 120;
            expirationTime = 6;
            space = 4;
        }
        else if(name.equals("lion"))
        {
            price = 300;
            expirationTime = 5;
            space = 15;
        }
        else if(name.equals("bear"))
        {
            price = 400;
            expirationTime = 5;
            space = 15;
        }
        else if(name.equals("tiger"))
        {
            price = 500;
            expirationTime = 5;
            space = 15;
        }
    }

    static void pickup(int x, int y)
    {
        for(int i=0; i<Product.list.size(); i++)
        {
            Product p=Product.list.get(i);
            if(p.x == x && p.y == y)
            {
                if (Warehouse.getInstance().add(p)) {
                    p.collected = true;
                    Task.claim(p.type);
                    Logger.write('i', p.type + " got picked up");
                    list.remove(p);//proposed
                }
                else {
                    Logger.write('e', "Warehouse did not have enough space");
                }
                return;
            }
        }
        System.out.println("product not found");
        Logger.write('e',"product not found");
    }

    static void expire()
    {
        for(int i=0; i<Product.list.size(); i++)
        {
            if(!Product.list.get(i).collected)
                Product.list.get(i).expirationTime--;
        }

        boolean b = true;
        while(b)
        {
            b = false;

            for(int i=0; i<Product.list.size(); i++)
            {
                if(Product.list.get(i).expirationTime<=0)
                {
                    Logger.write('i',Product.list.get(i).type+" got expired");
                    Product.list.remove(i);//proposed
                    b = true;
                    break;///????
                }
            }
        }
    }

}
public class SewingFactory extends Factory{
    SewingFactory(boolean addToList){
        super("sewing factory","fabric","shirt",400,6,addToList);
    }
}
import java.util.ArrayList;

public class Task {

    static ArrayList<Task> list = new ArrayList<>();

    String name;
    int claimed = 0;
    int goal;
    boolean complete = false;

    Task(String Name, int Goal)
    {
        name = Name;
        goal = Goal;
    }

    static void add(Mission mission)
    {
        Task.list.addAll(mission.tasks);
    }

    static void claim(String st)
    {
        for (int i=0; i<list.size(); i++)
        {
            if(st.equals(list.get(i).name) && !list.get(i).complete){
                if (list.get(i).name.equals("coins"))
                    list.get(i).claimed=Game.getCoins();
                else
                    list.get(i).claimed++;
            }
            if (list.get(i).claimed >= list.get(i).goal) {
                list.get(i).complete = true;
                list.get(i).claimed = list.get(i).goal;
            }
        }
    }

    static boolean check(int level)
    {
        boolean result  = true;
        for (int i=0; i<list.size(); i++)
        {
            if(!list.get(i).complete)
            {
                result = false;
                break;
            }
        }
        if(result) {
            User.current.unlockedLevels = Math.max(User.current.unlockedLevels, level + 1);
            User.levelUp(User.current);

            System.out.println("****** level complete ******");
            Logger.write('i',"level complete");
            clear();
        }

        return result;
    }

    static void clear()
    {
        Domestic.list.clear();
        Cat.list.clear();
        Hound.list.clear();
        Wild.list.clear();
        Product.list.clear();
        Task.list.clear();
        for (int i=0; i<6; i++)
            for (int j=0; j<6; j++)
                Plant.num[i][j] = 0;

    }
}
public class Time {

    static int time = 0;

    static void turn(int n) {
        for(int i=0; i<n; i++) {
            update();
            Time.time++;
        }
    }

    static void update() {
        appear();
        eat();
        Domestic.reduce();
        walk();
        eat();
        Product.expire();
        produce();
        collect();
        factoryUpdate();
        Truck.getInstance().update();
        removeCage();
        Well.getInstance().fillingProcess();
        Plant.warn();
        Output.show();
    }

    static void walk()
    {
        for(int i=0; i<Domestic.list.size(); i++)
        {
            Domestic domestic = Domestic.list.get(i);
            domestic.walk();
        }

        for(int i=0; i<Cat.list.size(); i++)
        {
            Cat cat = Cat.list.get(i);
            cat.walk();
        }

        for(int i=0; i<Hound.list.size(); i++)
        {
            Hound hound = Hound.list.get(i);
            hound.walk();
        }

        for(int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            wild.walk();
        }
    }

    static void eat()
    {
        for (int i=0; i<Domestic.list.size(); i++)
        {
            Domestic.list.get(i).eat();
        }
    }

    static void produce()
    {
        for (int i=0; i<Domestic.list.size(); i++)
        {
            Domestic.list.get(i).produce();
        }
    }

    static void removeCage()
    {
        for(int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            if( (wild.leftCages < wild.cages)&&(!wild.consecutiveCageOrder) )
                wild.leftCages++;
            wild.consecutiveCageOrder=false;
        }
    }

    static void collect()
    {
        for(int i=0; i<Cat.list.size(); i++)
        {
            Cat.list.get(i).collect();
        }
    }

    static void appear()
    {
        if(Game.mission.wilds.get(time) != null)
        {
            Wild.add(Game.mission.wilds.get(time));
        }
    }
    static void factoryUpdate(){
        for (Factory factory:Factory.factories){
            factory.update();
        }
    }

}
import java.util.HashMap;

public class Truck {
    private static Truck t;
    public static final int MAX_CAPACITY=15;
    public static final int TOTAL_DURATION=10;
    int timeLeft;
    int capacity;
    boolean loaded;
    int money;
    HashMap<String,Integer> productIntegerHashMap=new HashMap<>();
    HashMap<String,Integer> animalIntegerHashMap=new HashMap<>();
    Truck(){
        capacity=0;
        loaded=false;
        money=0;
        timeLeft=0;
    }
    public static Truck getInstance() {
        if (t==null)
            t=new Truck();
        return t;
    }
    public void TruckUnload(String item){
        if (Product.nameList.contains(item))
            unloadProduct(item);
        else{
            unloadAnimal(item);
        }
    }
    public void TruckLoad(String item){
        if (Truck.getInstance().timeLeft>0){
            System.out.println("Error: Truck is on the way");
            Logger.write('e',"Truck is on the way");
            return;
        }
        if (Product.nameList.contains(item))
            loadProduct(item);
        else
            loadAnimal(item);
    }
    private void loadProduct(String type){
        if (Warehouse.getInstance().inquiry(type,1)){
            Product p=new Product(type);
            if (capacity+p.space<=MAX_CAPACITY) {
                capacity+=p.space;
                if (productIntegerHashMap.containsKey(type))
                    productIntegerHashMap.put(type, productIntegerHashMap.get(type)+1);
                else
                    productIntegerHashMap.put(type, 1);
                Logger.write('i',p.type + " loaded");
            }
            else {
                System.out.println("not enough space in truck");
                Logger.write('e',"not enough space in truck");
            }
        }
    }
    private void loadAnimal(String name){
        HashMap<String,Integer> map=Animal.animalCount;
        if (map.containsKey(name)){
            Animal animal=new Domestic(name,false);
            if (name.equals("cat"))
                animal=new Cat(false);
            else if (name.equals("hound"))
                animal=new Hound();
            else if (!Domestic.nameList.contains(name)) {
                Logger.write('e',"Invalid item for loading");
                System.out.println("Invalid item for loading");
                return;
            }
            if ( (map.get(name)>0)&&(capacity+animal.space<=MAX_CAPACITY)) {
                capacity+=animal.space;
                if (!animalIntegerHashMap.containsKey(animal.type))
                    animalIntegerHashMap.put(animal.type, 0);
                animalIntegerHashMap.put(animal.type, animalIntegerHashMap.get(animal.type)+1);
                Logger.write('i',animal.type + " loaded");
            }
            else {
                System.out.println("not enough space in truck");
                Logger.write('e',"not enough space in truck");
            }
        }
    }

    private void unloadProduct(String type){
        if (productIntegerHashMap.containsKey(type)){
            Product p=new Product(type);
            if ( productIntegerHashMap.get(type)>0 ) {//&&(capacity+p.space<=MAX_CAPACITY) ??????
                capacity-=p.space;
                productIntegerHashMap.put(type, productIntegerHashMap.get(type)-1);
                Logger.write('i',p.type + " unloaded");
            }
        }
    }

    private void unloadAnimal(String name){
        HashMap<String,Integer> map=animalIntegerHashMap;
        if (map.containsKey(name)){
            Animal animal=new Domestic(name,false);
            if (name.equals("cat"))
                animal=new Cat(false);
            else if (name.equals("hound"))
                animal=new Hound();
            if ( map.get(name)>0 ) { //&&(capacity+animal.space<=MAX_CAPACITY) ??????
                capacity-=animal.space;
                animalIntegerHashMap.put(animal.type, map.get(animal.type)-1);
                Logger.write('i',animal.type + " unloaded");
            }
        }
    }
    public void confirm(){
        if (productIntegerHashMap.isEmpty()&&animalIntegerHashMap.isEmpty()){
            Logger.write('e',"Empty truck cannot leave");
            System.out.println("Empty truck cannot leave");
            return;
        }
        for (String type:productIntegerHashMap.keySet()) {
            Product p=new Product(type);
            Warehouse.getInstance().remove(p,productIntegerHashMap.get(type));
            money+=p.price*productIntegerHashMap.get(type);
        }
        for (String name: animalIntegerHashMap.keySet()) {
            Animal animal=new Domestic(name,false);
            if (name.equals("cat"))
                animal=new Cat(false);
            else if (name.equals("hound"))
                animal=new Hound();
            for (int i = 0; i < animalIntegerHashMap.get(name); i++) {
                Animal.removeFromMap(name);
                if(name.equals("cat"))
                    Cat.list.remove(0);
                else if(name.equals("hound"))
                    Hound.list.remove(0);
                else {
                    for (int j=0; j<Domestic.list.size(); j++) {
                        if(Domestic.list.get(j).type.equals(name)) {
                            Domestic.list.remove(j);
                            break;
                        }
                    }
                }
            }
            money+=animal.price*animalIntegerHashMap.get(name);
        }
        timeLeft=TOTAL_DURATION;
        animalIntegerHashMap.clear();
        productIntegerHashMap.clear();
        capacity=0;
        Logger.write('i', "truck left");
        System.out.println("Truck left");
    }
    public void update(){
        if (timeLeft==0){
            Game.addCoins(money);
            money=0;
            return;
        }
        timeLeft--;
    }
}
import com.google.gson.Gson;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Scanner;

public class User {

    static ArrayList<User> list = new ArrayList<>();

    static User current;
    String userName;
    String password;
    int unlockedLevels = 1;
    int points;

    User(String name, String pass)
    {
        userName = name;
        password = pass;
        points=0;
    }

    static void load()
    {
        try
        {
            FileReader reader = new FileReader("users.txt");
            Scanner sc = new Scanner(reader);
            String str = "";

            while (sc.hasNextLine())
            {
                str += sc.nextLine();
                if(str.charAt(str.length()-1) == '*')
                {
                    str = str.substring(0, str.length()-1);
                    //reader.close();
                    Gson gson = new Gson();
                    User user = gson.fromJson(str, User.class);
                    User.list.add(user);
                    str = "";
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {}
    }

    static User login(String name, String password)
    {
        for (int i=0; i<User.list.size(); i++)
        {
            if(User.list.get(i).userName.equals(name))
            {
                if(User.list.get(i).password.equals(password))
                {
                    User.current = User.list.get(i);
                    System.out.println("login successful");
                    Logger.write('i',"login successful");
                    return User.list.get(i);
                }
                else
                {
                    System.out.println("wrong password");
                    Logger.write('e',"wrong password");
                    return null;
                }
            }
        }
        System.out.println("username not found");
        Logger.write('e',"username not found");
        return null;
    }

    static User signup(String name, String password)
    {
        User newUser = new User(name, password);
        if(!User.list.contains(newUser))
            list.add(newUser);
        else
        {
            System.out.println("username already exists");
            Logger.write('e',"username already exists");
            return null;
        }
        //omit start
        String st = "";
        /*
        try
        {

            FileReader reader = new FileReader("users.txt");
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine())
            {
                st += sc.nextLine();
            }
            reader.close();
        }
        catch (IOException e)
        {}
        //omit end

         */
        try
        {
            FileWriter writer = new FileWriter("users.txt",true);//new FileWriter("users.txt",true);
            String json = new Gson().toJson(newUser);
            writer.write(st + json + "*\n");
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
            Logger.write('e',"error");
        }
        User.current = newUser;
        System.out.println("signup successful");
        Logger.write('i', "signup successful");
        return newUser;
    }

    static void levelUp(User user)
    {
        String st = "";
        String s;
        user.points+=Game.mission.reward;
        HashMap<Integer,Integer> map=Game.mission.TimeBonus;
        for (Integer time:map.keySet()){
            if (Time.time<=time){
                user.points+=map.get(time);
            }
        }
        try {
            FileReader reader = new FileReader("users.txt");
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine())
            {
                s = sc.nextLine();
                if(!s.contains(user.userName))
                    st += s;
            }
            reader.close();
        }
        catch (IOException e)
        {}

        try
        {
            FileWriter writer = new FileWriter("users.txt");
            String json = new Gson().toJson(user);
            writer.write(st + json + "*\n");
            writer.close();
            User.list.clear();
            User.load();
        }
        catch (IOException e)
        {
            System.out.println("error");
            Logger.write('e',"error");
        }
    }
}
import java.util.ArrayList;
        import java.util.HashMap;

public class Warehouse {
    private static Warehouse w;
    int capacity;
    int occupied;
    ArrayList<Product> products=new ArrayList<>();
    private HashMap<String,Integer> productIntegerHashMap=new HashMap<>();
    Warehouse(){
        capacity=30;
    }

    public static Warehouse getInstance() {
        if (w==null)
            w=new Warehouse();
        return w;
    }

    public boolean add(Product product){
        if (occupied+product.space<capacity) {
            //Product.list.remove(product);
            occupied += product.space;
            for (Product product1 : products) {
                if (product1.type.equals(product.type)) {
                    productIntegerHashMap.put(product1.type, productIntegerHashMap.get(product1.type) + 1);
                    return true;
                }
            }
            products.add(product);
            productIntegerHashMap.put(product.type, 1);
            return true;
        }
        return false;
    }
    public boolean remove(Product product, int number){
        for (Product product1:products){
            int num=productIntegerHashMap.get(product1.type);
            if (product.type.equals(product1.type) && num>=number){
                occupied-=product.space;
                productIntegerHashMap.put(product1.type,num-number);
                if (num-number==0) {
                    productIntegerHashMap.remove(product1.type);
                    products.remove(product1);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean inquiry(String type,int number){
        if (!productIntegerHashMap.containsKey(type))
            return false;
        return productIntegerHashMap.get(type) >= number;
    }
    public void show(){
        System.out.println("----Warehouse----");
        System.out.println("Occupied space: "+occupied);
        for (Product product:Warehouse.getInstance().products){
            System.out.println(product.type+" "+productIntegerHashMap.get(product.type));
        }
    }
}
public class Weaver extends Factory{
    Weaver(boolean addToMap){
        super("weaver","feather","fabric",250,5,addToMap);
    }
}
public class Well {
    private static Well w;
    private int capacity;
    private boolean process;
    private int counter;
    public static final int FILL_TURN=3;
    private Well(){
        counter=0;
        process=false;
        capacity=5;
    }
    public static Well getInstance() {
        if (w==null)
            w=new Well();
        return w;
    }
    public void water(int x, int y){
        if (capacity>0){
            Plant.num[x-1][y-1]++;
            capacity--;
            Logger.write('i',"planted successfully");
            System.out.println("planted successfully");
        }
        else {
            Logger.write('e',"Error: not enough water in the well");
            System.out.println("Error: not enough water in the well");
        }

    }
    public void fill(){
        if (capacity==0) {
            process=true;
            return;
        }
        Logger.write('e',"Error: Well is not empty and cannot be filled");
        System.out.println("Error: Well is not empty and cannot be filled");
    }
    public void fillingProcess(){
        if ((process)&&(counter<FILL_TURN))
            counter++;
        if (counter==FILL_TURN) {
            capacity = 5;
            process = false;
            counter=0;
        }
    }
}
import java.util.ArrayList;
        import java.util.Set;

public class Wild extends Animal{

    static ArrayList<Wild> list = new ArrayList<>();
    public static final Set<String> nameList=Set.of("bear","tiger","lion");

    int cages;
    int leftCages;
    boolean consecutiveCageOrder;

    Wild(String name)
    {
        consecutiveCageOrder=false;
        name = name.toLowerCase();
        if(name.equals("lion"))
        {
            type = "lion";
            price = 300;
            step = 1;
            space =15;
            cages = 3;
            leftCages = 3;
            x = random();
            y = random();
        }

        else if(name.equals("bear"))
        {
            type = "bear";
            price = 400;
            step = 1;
            space =15;
            cages = 4;
            leftCages = 4;
            x = random();
            y = random();
        }

        else if(name.equals("tiger"))
        {
            type = "tiger";
            price = 500;
            step = 2;
            space =1;
            cages = 4;
            leftCages = 4;
            x = random();
            y = random();
        }
    }

    static void add(String name)
    {
        Wild.list.add(new Wild(name));
    }

    void kill()
    {
        Wild.list.remove(this);
        Logger.write('i',type+" got killed");
    }

    void walk()
    {
        int d = randomDirection();
        for(int k=1; k<=step; k++)
        {

            if((d==0)&&(x<6))
                x++;
            else if((d==1)&&(y<6))
                y++;
            else if((d==2)&&(x>1))
                x--;
            else if((d==3)&&(y>1))
                y--;

            for(int j=0; j<Hound.list.size(); j++)
            {
                Hound hound = Hound.list.get(j);
                if(hound.x == x && hound.y == y)
                {
                    hound.kill();
                    kill();
                    return;
                }
            }

            for(int j=0; j<Domestic.list.size(); j++)
            {
                Domestic domestic = Domestic.list.get(j);
                if(domestic.x == x && domestic.y == y)
                {
                    domestic.kill();
                    break;
                }
            }

            for(int j=0; j<Cat.list.size(); j++)
            {
                Cat cat = Cat.list.get(j);
                if(cat.x == x && cat.y == y)
                {
                    cat.kill();
                    break;
                }
            }


        }
    }

    static void cage(int x, int y)
    {
        for (int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            if(wild.x == x && wild.y == y)
            {
                wild.consecutiveCageOrder=true;
                wild.leftCages--;
                if(wild.leftCages<=0)
                {
                    Logger.write('i',Wild.list.get(i).type+" got caged");
                    Product newProduct = new Product(wild.type);
                    newProduct.x = wild.x;
                    newProduct.y = wild.y;
                    newProduct.collected = false;
                    Product.list.add(newProduct);
                    Wild.list.remove(wild);
                }
                return;
            }
        }
    }
}