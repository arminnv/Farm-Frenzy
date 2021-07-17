import java.awt.image.BufferedImage;

public abstract class Incubator extends Factory{
    protected Incubator(String name, String validType, String outputType, int buildingCost, int productionDefaultDuration, BufferedImage[] image, boolean alignment, boolean addToList){
        super(name, validType, outputType, buildingCost, productionDefaultDuration, image, alignment, addToList);
    }
    public void update(){
        if (productionDuration>0){
            productionDuration-=Time.dt;
            factoryGraphics.jProgressBar.setValue((int)(productionDefaultDuration-productionDuration));
        }
        if (productionDuration<=0){
            for(int i=0;i<underProduction;i++){
                Domestic domestic=Domestic.newDomestic(outputType,true);
                Logger.write('i',this.name+ " produced "+domestic.type);
            }
            underProduction=0;
            this.factoryGraphics.jProgressBar.setValue(0);
        }
    }
}