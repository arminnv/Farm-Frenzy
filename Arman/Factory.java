import java.lang.reflect.Type;
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
        if (name.equals("Mill"))
            factory=new Mill(addToList);
        else if (name.equals("Weaver"))
            factory=new Weaver(addToList);
        else if (name.equals("Sewing factory"))
            factory=new SewingFactory(addToList);
        else if (name.equals("Milk packaging factory"))
            factory=new MilkPackagingFactory(addToList);
        else if (name.equals("Ice cream factory"))
            factory=new IceCreamFactory(addToList);
        else if (name.equals("Bakery"))
            factory=new Bakery(addToList);
        return factory;
    }
    private void produce(int number) {
        if ((number!=0)&&Warehouse.getInstance().inquiry(validType,number)){
            productionDuration= (int)Math.ceil(productionDefaultDuration*number*1.0/level);
            Warehouse.getInstance().remove(new Product(validType),number);
            underProduction=number;//Class.forName(outputType.getTypeName()).getDeclaredConstructor().newInstance()
            System.out.println(outputType);///omit later
            return;
        }
        Logger.write('e',"Product cannot be produced");
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
                int a=1;//debug
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
