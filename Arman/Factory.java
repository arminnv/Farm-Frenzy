import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public abstract class Factory {
    public static final String[] nameList={"mill","weaver","milk packaging factory","hen incubator","sewing factory","ice cream factory","bakery"};
    static ArrayList<Factory> factories=new ArrayList<>();
    static Random r=new Random();
    int level;
    int maxAllowedLevel;
    int buildingCost;
    int upgradeCost;
    double productionDefaultDuration;
    double productionDuration;
    String name;
    String validType;
    String outputType;
    int underProduction;
    FactoryWellGraphics factoryGraphics;
    protected Factory(String name, String validType, String outputType, int buildingCost, int productionDefaultDuration,  BufferedImage[] image, boolean alignment,boolean addToList){
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
        factoryGraphics=new FactoryWellGraphics(2.7,true,image,alignment);
        factoryGraphics.jProgressBar.setMaximum((int)(productionDefaultDuration/Time.DT));
        //TODO
        factoryGraphics.mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Factory.this.produce();
            }
        });
        factoryGraphics.upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Factory.this.upgrade();
            }
        });
        if (addToList) {
            factories.add(this);
        }
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
        else if (name.equals("hen incubator"))
            factory=new HenIncubator(addToList);
        return factory;
    }
    protected void produce() {
        for (int number=level;number>=1;number--) {
            if (Warehouse.getInstance().inquiry(validType, number)&&(underProduction==0)) {
                productionDuration = (int) Math.ceil(productionDefaultDuration * number * 1.0 / level);
                Warehouse.getInstance().remove(Product.newProduct(validType), number);
                underProduction = number;//Class.forName(outputType.getTypeName()).getDeclaredConstructor().newInstance()
                System.out.println("Product is getting produced");
                Logger.write('i', "Product is getting produced");
                return;
            }
        }
        System.out.println("Product cannot be produced");
        Logger.write('e',"Product cannot be produced");
    }
    protected void upgrade(){
        if ( (Game.getCoins()>upgradeCost)&&(level+1<=maxAllowedLevel) ){
            Game.addCoins(-upgradeCost);
            level++;
            factoryGraphics.setMainImage(level);
            if (level==maxAllowedLevel)
                factoryGraphics.setUpgrade(false);
            Logger.write('i',"Factory "+name+" was upgraded to level "+level);
            System.out.println("Factory "+name+" was upgraded to level "+level);
            return;
        }
        System.out.println("Insufficient money or max level error");
        Logger.write('e',"Insufficient money or max level error");
    }
    public void update(){
        if (productionDuration>0){
            productionDuration-=Time.dt;
            factoryGraphics.jProgressBar.setValue((int) ((productionDefaultDuration-productionDuration)/Time.DT) );
        }
        if (productionDuration<=0){
            for(int i=0;i<underProduction;i++){
                Product product=Product.newProduct(outputType);
                product.x=(double) (r.nextInt(6))+0.5;
                product.y=(double) (r.nextInt(6))+0.5;
                Product.list.add(product);
                Logger.write('i',this.name+ " produced "+product.type);
            }
            underProduction=0;
            this.factoryGraphics.jProgressBar.setValue(0);
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
    public static void produce(String factoryName){
        for (Factory factory:factories){
            if (factory.name.equals(factoryName)) {
                factory.produce();
                return;
            }
        }
        System.out.println("Factory was not found");
        Logger.write('e',"Factory was not found");
    }
    public static boolean build(String name,JFrame jFrame){
        System.out.println(name);
        for (String name1:nameList){
            if (name1.equals(name)){
                for (Factory factory:factories){
                    if (factory.name.equals(name)){
                        System.out.println("Factory already exists");
                        Logger.write('e',"Factory already exists");
                        return false;
                    }
                }
                Factory f=createFactory(name,false);
                if (f!=null){//name is always valid so this is always true
                    if (f.buildingCost<=Game.getCoins()){
                        f=createFactory(name,true);
                        Game.addCoins(-f.buildingCost);
                        Container container=jFrame.getContentPane();
                        container.add(f.factoryGraphics.jPanel);
                        System.out.println("Factory was built successfully");
                        Logger.write('i',"Factory was built successfully");
                        return true;
                    }
                }
                System.out.println("Error");
                return false;
            }
        }
        System.out.println("Factory name is invalid");
        Logger.write('e',"Factory name is invalid");
        return false;
    }
    public static void deleteFactories(){
        factories=new ArrayList<>();
    }
}