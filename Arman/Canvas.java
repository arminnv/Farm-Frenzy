import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Canvas extends JComponent{
    static int h = 800;
    static int w = 1000;
    static int landW = 400;
    static int landH = 400;
    JFrame frame = new JFrame("Farm Frenzy");
    JPanel mousePanel=new JPanel();
    static int y0 ;

    void setFrame()
    {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        Images.load();
        System.out.println("setFrame end");//
        Container container=frame.getContentPane();
        frame.setLayout(new GroupLayout(container));

        JButton jButton=Truck.getInstance().jButton;
        //TODO
        //to check the panel dimensions, uncomment these lines
        mousePanel.setOpaque(true);
        //mousePanel.setVisible(true);
        //
        Product p1= new Product();
        Product p2= new Product();
        p1.x = 0;
        p1.y = 6;
        p2.x = 6;
        p2.y = 0;
        System.out.println(p1.xScale());
        System.out.println(p1.yScale());
        System.out.println(p2.xScale());
        System.out.println(p2.yScale());
        mousePanel.setBounds(p1.xScale(),p1.yScale(),p2.xScale()-p1.xScale(),p2.yScale()-p1.yScale());
        mousePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InputProcessor.search(e.getX(),e.getY());
                super.mouseClicked(e);
            }
        });

        Game.myClock.timeLabel.setBounds(850,670,130,40);
        container.add(jButton);
        container.add(mousePanel);
        container.add(Game.myClock.timeLabel);
        container.add(Well.getInstance().wellGraphics.jPanel);
    }

    Canvas()
    {
        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint();
                    revalidate();
                    Well.getInstance().wellGraphics.jPanel.repaint();
                    try {Thread.sleep(10);} catch (Exception ex) {
                        ex.printStackTrace();
                    }
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

            paintLand(gg);
            Well.getInstance().wellGraphics.jPanel.repaint();
            Truck.getInstance().jButton.repaint();
            paintGrass(gg);
            paintDomestics(gg);
            paintProducts(gg);
            paintWilds(gg);
            paintCats(gg);
            paintHounds(gg);
            Game.myClock.setTimeLabel();
            //TODO
            //uncomment to see jpanel
            //mousePanel.repaint();

            //frame.repaint();
        }

    }

    static void paintLand(Graphics2D gg)
    {

        gg.setColor(Color.RED);
        gg.drawImage(Images.land,0,0,w,getH(Images.land,w),null);
        int lh = getH(Images.land,w);
        y0 = (int)(h/2 - (double)(lh)/20 - lh/2);
        Product p = new Product();
        p.x = 6;
        p.y = 6;
        gg.drawOval(p.xScale()-10,p.yScale()-10,20,20);
        p.x = 0;
        p.y = 0;
        gg.drawOval(p.xScale()-10,p.yScale()-10,20,20);
        p.x = 0;
        p.y = 6;
        gg.drawOval(p.xScale()-10,p.yScale()-10,20,20);
        p.x = 6;
        p.y = 0;
        gg.drawOval(p.xScale()-10,p.yScale()-10,20,20);
        Well.getInstance().wellGraphics.jPanel.repaint();

    }

    static void paintDomestics(Graphics2D gg)
    {
        for (int i=0; i<Domestic.list.size(); i++)
        {
            //System.out.println("***********");
            Domestic domestic = Domestic.list.get(i);
            BufferedImage image;
            image = Images.land;
            if(domestic.type.equals("chicken"))
            {
                image = Images.chicken[domestic.state];
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
            //gg.drawImage(image,(int)(100*domestic.x),(int)(100*domestic.y),domestic.w,domestic.h,null);
        }
    }

    static void paintWilds(Graphics2D gg)
    {
        for (int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            BufferedImage image = null;
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
    }

    static void paintCats(Graphics2D gg)
    {
        for (int i=0; i<Cat.list.size(); i++)
        {
            Cat cat = Cat.list.get(i);
            BufferedImage image = Images.cat[cat.state];

            gg.drawImage(image,cat.xScale(),cat.yScale(),cat.w,cat.h,null);
        }
    }

    static void paintHounds(Graphics2D gg)
    {
        for (int i=0; i<Hound.list.size(); i++)
        {
            Hound hound = Hound.list.get(i);
            BufferedImage image = Images.hound[hound.state];

            gg.drawImage(image,hound.xScale(),hound.yScale(),hound.w,hound.h,null);
        }
    }

    static void paintProducts(Graphics2D gg)
    {
        for (Product product:Product.list) {
            gg.drawImage(product.image, product.xScale(),product.yScale(),product.w,product.h,null);
        }
    }

    static void paintGrass(Graphics2D gg)
    {
        BufferedImage image = Images.grass;
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<6; j++)
            {
                for (int k=0; k<Plant.num[i][j]; k++)
                {//40
                    gg.drawImage(image,xScale(i+0.5,30),yScale(j+0.5,30),66,66,null);
                }
            }
        }
    }

    static int getH(BufferedImage image, int w)
    {
        return (int) ((double)image.getHeight()/(double)image.getWidth() * w );
    }
    static int getW(BufferedImage image, int h)
    {
        return (int) ((double)image.getWidth()/(double)image.getHeight() * h );
    }

    static int xScale(double x, int w)
    {
        int X = (int)(Canvas.w/2 + Canvas.landW*(x/6-0.5) - w/2 );
        return X;
    }

    static int yScale(double y, int h)
    {
        int Y = (int)( Canvas.h - (Canvas.h/2 + Canvas.landH*(y/6-0.5) +y0 ) - h/2  );
        return Y;
    }


}
