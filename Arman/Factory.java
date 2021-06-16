import java.lang.reflect.Type;

public class Factory {
    int level;
    int maxAllowedLevel;
    int buildingCost;
    int upgradeCost;
    int productionDefaultDuration;
    int productionDuration;
    Type validType;
    Type outputType;
    int underProduction;
    Factory(Type validType,Type outputType,int buildingCost, int productionDefaultDuration){
        this.outputType=outputType;
        this.validType=validType;
        this.buildingCost=buildingCost;
        this.productionDefaultDuration=productionDefaultDuration;
        this.productionDuration=0;
        this.upgradeCost=buildingCost*3/2;
        underProduction=0;
        level=1;
    }
    public void produce(Product product,int number) {
        if (product.getClass().equals(validType))
        try {
            if (level>1){

            }
            underProduction=number;
            System.out.println(Class.forName(outputType.getTypeName()).getDeclaredConstructor().newInstance());
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Not successful");
        }
    }
    public void update(){
        if (underProduction>0)
    }
}
