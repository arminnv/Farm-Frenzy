import java.awt.image.BufferedImage;

public abstract class Incubator extends Factory{
    Incubator(String name, String validType, String outputType, int buildingCost, int productionDefaultDuration, int x, int y, BufferedImage image, boolean alignment, boolean addToList){
        super(name, validType, outputType, buildingCost, productionDefaultDuration, x, y, image, alignment, addToList);
    }
    public void update(){
        if (productionDuration>0){
            productionDuration-=Time.dt;
        }
        if (productionDuration==0){
            for(int i=0;i<underProduction;i++){
                Domestic domestic=Domestic.newDomestic(outputType,true);
                Logger.write('i',this.name+ " produced "+domestic.type);
            }
            underProduction=0;
        }
    }
}