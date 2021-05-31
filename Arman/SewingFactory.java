public class SewingFactory extends Factory{
    SewingFactory(){
        validType=Cloth.class;
        buildingCost=400;
        productionDuration=6;
        outputType=Shirt.class;
    }
}
