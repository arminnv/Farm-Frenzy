import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public abstract class Factory {
    int level;
    int maxAllowedLevel;
    int buildingCost;
    int upgradeCost;
    int productionDuration;
    Type validType;
    Type outputType;
    public void produce(Product product,int number){

    }
}
