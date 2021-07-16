import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Well {
    private static Well w;
    private int capacity;
    private boolean process;
    private int counter;
    FactoryWellGraphics wellGraphics;
    public static final int FILL_TURN=5;
    private Well(){
        wellGraphics=new FactoryWellGraphics(3.0,false,Images.well,FactoryWellGraphics.RIGHT_ALIGNMENT);
        counter=0;
        process=false;
        capacity=5;
        wellGraphics.jProgressBar.setMaximum(capacity);
        wellGraphics.jProgressBar.setValue(capacity);
        //capacity=0;//omit later
        wellGraphics.jPanel.setBounds(390,90,(int)(FactoryWellGraphics.X_SCALE*wellGraphics.scale),(int)(FactoryWellGraphics.Y_SCALE* wellGraphics.scale));
        wellGraphics.mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Well.getInstance().fill();
            }
        });
    }
    public static Well getInstance() {
        if (w==null)
            w=new Well();
        return w;
    }
    public void water(double x, double y){
        System.out.println(x+" "+y);
        if ((capacity>0)&&(x>0)&&(x<6) &&(y>0)&&(y<6)){
            Plant.num[(int)x][(int)y]++;
            capacity--;
            Logger.write('i',"planted successfully");
            System.out.println("planted successfully");
            wellGraphics.jProgressBar.setValue(capacity);
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
        if ((process)&&(counter<FILL_TURN)) {
            counter++;
            wellGraphics.jProgressBar.setValue(counter);
        }
        if (counter==FILL_TURN) {
            capacity = 5;
            process = false;
            counter=0;
        }
    }
}