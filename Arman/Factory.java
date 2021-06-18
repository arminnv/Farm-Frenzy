import java.lang.reflect.Type;
import java.util.ArrayList;

public class Factory {
    public static final String[] nameList={"Mill","Weaver","Sewing factory","Milk packaging factory","Ice cream factory","Bakery"};
    static ArrayList<Factory> factories=new ArrayList<>();
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
        factories.add(this);
    }
    private Factory(String name){
        if (name.equals("Mill"))
            new Mill();
        else if (name.equals("Weaver"))
            new Weaver();
        else if (name.equals("Sewing factory"))
            new SewingFactory();
        else if (name.equals("Milk packaging factory"))
            new MilkPackagingFactory();
        else if (name.equals("Ice cream factory"))
            new IceCreamFactory();
        else if (name.equals("Bakery"))
            new Bakery();
    }
    private void produce(int number) {
        if ((number!=0)&&Warehouse.getInstance().inquiry(validType,number)){
            productionDuration= (int)Math.ceil(productionDefaultDuration*number*1.0/level);
            Warehouse.getInstance().remove(new Product(validType),number);
            underProduction=number;//Class.forName(outputType.getTypeName()).getDeclaredConstructor().newInstance()
            System.out.println(outputType);
            return;
        }
        Logger.write('e',"Product cannot be produced");
    }
    public void update(){
        if (productionDuration>0){
            productionDuration--;
        }
        if (productionDuration==0){
            for(int i=0;i<underProduction;i++){
                new Product(validType);
            }
        }
    }
    private void upgrade(){
        if ( (Game.getCoins()>upgradeCost)&&(level+1<=maxAllowedLevel) ){
            Game.addCoins(-upgradeCost);
            level++;
            Logger.write('i',"Factory"+name+"was upgraded to level "+level);
            System.out.println("Factory"+name+"was upgraded to level "+level);
            return;
        }
        System.out.println("Insufficient money or max level error");
        Logger.write('e',"Insufficient money or max level error");
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
            if (factory.name.equals(factoryName))
                factory.produce(number);
        }
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
                new Factory(name);
                System.out.println("Factory was built successfully");
                Logger.write('i',"Factory was built successfully");
            }
        }
        System.out.println("Factory name is invalid");
        Logger.write('e',"Factory name is invalid");
    }
}
