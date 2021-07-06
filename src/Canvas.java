import javax.swing.*;
import java.awt.*;

public class Canvas extends JComponent{
    int h;
    int w;

    void frame()
    {
        JFrame frame = new JFrame("Farm Frenzy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        frame.add(new Canvas());
        frame.setVisible(true);
    }

    Canvas()
    {
        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint();
                    try {Thread.sleep(10);} catch (Exception ex) {}
                }
            }
        });
        animationThread.start();
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D gg = (Graphics2D) g;
        gg.setColor(Color.WHITE);
    }


}
