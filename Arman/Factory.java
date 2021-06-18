import java.lang.reflect.Type;
import java.util.ArrayList;

public class Factory {
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
    public static void Initialise(){
        new SewingFactory();
        new Mill();
        new Weaver();
        new Bakery();
        new MilkPackagingFactory();
        new IceCreamFactory();
    }
    Factory(String name, String validType,String outputType,int buildingCost, int productionDefaultDuration){
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
    public void upgrade(){
        if ( (Game.getCoins()>upgradeCost)&&(level+1<=maxAllowedLevel) ){
            Game.addCoins(-upgradeCost);
            level++;
            Logger.write('i',"Factory"+name+"was upgraded to level "+level);
        }
    }
    public static void produce(String factoryName,int number){
        for (Factory factory:factories){
            if (factory.name.equals(factoryName))
                factory.produce(number);
        }
    }
}
