public abstract class Incubator extends Factory{
    Incubator(String name, String validType,String outputType,int buildingCost, int productionDefaultDuration, boolean addToList){
        super(name, validType, outputType, buildingCost, productionDefaultDuration, addToList);
    }
    public void update(){
        if (productionDuration>0){
            productionDuration--;
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