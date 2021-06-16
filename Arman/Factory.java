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
    public void produce(Product product,int number) {
        if (product.type.equals(validType) &&(number!=0)){
            productionDuration= (int)Math.ceil(productionDefaultDuration*number*1.0/level);
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

            }
        }
    }
    public void upgrade(){
        if ( (Game.coins>upgradeCost)&&(level+1<=maxAllowedLevel) ){
            Game.coins -= upgradeCost;
            level++;
            Logger.write('i',"Factory"+name+"was upgraded to level "+level);
        }
    }
}
