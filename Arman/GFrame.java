import javax.swing.*;
import java.awt.*;

public class GFrame{
    static int h = 800;
    static int w = 1000;
    JFrame frame=new JFrame("Farm Frenzy");
    void setFrame(Canvas canvas) {
        Images.load();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        Container container=frame.getContentPane();
        frame.setLayout(new GroupLayout(container));
        container.add(Well.getInstance().wellGraphics.jPanel);
        frame.add(canvas);
        frame.setVisible(true);

        System.out.println("setFrame end");

    }
}
