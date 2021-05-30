import java.lang.reflect.Type;

public abstract class Factory {
    int level;
    int maxAllowedLevel;
    int buildingCost;
    int upgradeCost;
    int productionDuration;
    Type validType;
}
