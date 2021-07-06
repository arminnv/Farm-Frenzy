import javax.swing.*;
import java.awt.*;

public class Canvas extends JComponent{
    int h;
    int w;
    int landW;
    int landH;
    JFrame frame = new JFrame("Farm Frenzy");

    void setFrame()
    {
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

        if(Menu.game)
        {
            gg.drawImage(Images.land,0,0,w,h,null);

            for (int i=0; i<Domestic.list.size(); i++)
            {
                Domestic domestic = Domestic.list.get(i);
                Image image = null;
                if(domestic.type.equals("cow"))
                {
                    image = Images.cow[domestic.state];
                }
                else if(domestic.type.equals("turkey"))
                {
                    image = Images.turkey[domestic.state];
                }
                else if(domestic.type.equals("buffalo"))
                {
                    image = Images.buffalo[domestic.state];
                }

                gg.drawImage(image,domestic.xScale(),domestic.yScale(),domestic.w,domestic.h,null);
            }

            for (int i=0; i<Wild.list.size(); i++)
            {
                Wild wild = Wild.list.get(i);
                Image image = null;
                if(wild.type.equals("lion"))
                {
                    image = Images.lion[wild.state];
                }
                else if(wild.type.equals("bear"))
                {
                    image = Images.bear[wild.state];
                }
                else if(wild.type.equals("tiger"))
                {
                    image = Images.tiger[wild.state];
                }

                gg.drawImage(image,wild.xScale(),wild.yScale(),wild.w,wild.h,null);
            }

            for (int i=0; i<Cat.list.size(); i++)
            {
                Cat cat = Cat.list.get(i);
                Image image = Images.cat[cat.state];

                gg.drawImage(image,cat.xScale(),cat.yScale(),cat.w,cat.h,null);
            }

            for (int i=0; i<Hound.list.size(); i++)
            {
                Hound hound = Hound.list.get(i);
                Image image = Images.cat[hound.state];

                gg.drawImage(image,hound.xScale(),hound.yScale(),hound.w,hound.h,null);
            }

        }

    }


}
