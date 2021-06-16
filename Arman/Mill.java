public class Mill extends Factory{
    Mill(){
        super(Egg.class,Flour.class,150,4);
        validType=Egg.class;
        buildingCost=150;
        productionDuration=4;
        outputType=Flour.class;
    }
}
