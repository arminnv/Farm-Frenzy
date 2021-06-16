public class Well {
    private static Well w;
    private int capacity;
    private boolean process;
    private int counter;
    public static final int FILL_TURN=3;
    private Well(){
        counter=0;
        process=false;
        capacity=5;
    }
    public static Well getInstance() {
        if (w==null)
            w=new Well();
        return w;
    }
    public void water(int x, int y){
        if (capacity>0){
            Plant.num[x-1][y-1]++;
            Logger.write('i',"planted successfully");
        }
        else {
            Logger.write('e',"Error: not enough water in the well");
        }

    }
    public void fill(){
        if (capacity==0) {
            process=true;
            return;
        }
        //not empty
        Logger.write('e',"Error: Well is not empty and cannot be filled");
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
