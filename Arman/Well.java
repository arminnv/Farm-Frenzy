public class Well {
    private static Well w;
    private int capacity;
    private boolean process;
    private int counter;
    FactoryWellGraphics wellGraphics;
    public static final int FILL_TURN=3;
    private Well(){
        wellGraphics=new FactoryWellGraphics(3.0,400,50,false,Images.well,FactoryWellGraphics.RIGHT_ALIGNMENT);
        counter=0;
        process=false;
        capacity=5;
    }
    public static Well getInstance() {
        if (w==null)
            w=new Well();
        return w;
    }
    public void water(double x, double y){
        if (capacity>0){
            Plant.num[(int)x][(int)y]++;
            capacity--;
            Logger.write('i',"planted successfully");
            System.out.println("planted successfully");
        }
        else {
            Logger.write('e',"Error: not enough water in the well");
            System.out.println("Error: not enough water in the well");
        }

    }
    public void fill(){
        if (capacity==0) {
            process=true;
            return;
        }
        Logger.write('e',"Error: Well is not empty and cannot be filled");
        System.out.println("Error: Well is not empty and cannot be filled");
    }
    public void fillingProcess(){
        if ((process)&&(counter<FILL_TURN))
            counter++;
        if (counter==FILL_TURN) {
            capacity = 5;
            process = false;
            counter=0;
        }
    }
}