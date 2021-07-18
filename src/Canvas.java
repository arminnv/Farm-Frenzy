import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Canvas extends JComponent{
    static int h = 800;
    static int w = 1000;
    static int landW = 400;
    static int landH = 400;
    static JFrame frame = new JFrame("Farm Frenzy");
    JPanel mousePanel=new JPanel();
    JButton pause=new JButton();
    JButton tasks=new JButton("Tasks");
    JLabel coins=new JLabel();
    static int y0 ;
    ArrayList<JButton> jButtons=new ArrayList<>();
    ArrayList<JButton> factoryButtons=new ArrayList<>();
    Canvas() {
        frame = new JFrame("Farm Frenzy");
        mousePanel=new JPanel();
        pause=new JButton();
        tasks=new JButton("Tasks");
        coins=new JLabel();
        jButtons=new ArrayList<>();
        factoryButtons=new ArrayList<>();
        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint();
                    revalidate();
                    Well.getInstance().wellGraphics.jPanel.repaint();
                    try {Thread.sleep(5);} catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        animationThread.start();
    }

    void setFrame() {
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
        mousePanel.setVisible(true);
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

        jButtons=new ArrayList<>();
        for (int i=0;i<Animal.purchasable.length;i++){
            String s=Animal.purchasable[i];
            JButton b=new JButton(s);
            b.setName(s);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Domestic.buy(b.getName());
                }
            });
            //TODO
            /*
            ImageIcon icon=new ImageIcon(Images.getAnimalImage(s));
            b.setIcon(FactoryWellGraphics.resizeIcon(icon,100,50));
             */
            //b.setIcon(new ImageIcon(Images.getAnimalImage(s)));
            b.setBounds(10+100*i,10,100,50);
            jButtons.add(b);
            container.add(b);
        }
        factoryButtons=new ArrayList<>();
        for (int i=0;i<Factory.nameList.length;i++){
            String s=Factory.nameList[i];
            JButton b=new JButton();
            b.setName(s);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean b1=Factory.build(b.getName(),Canvas.frame);
                    FactoryWellGraphics graphics;
                    if (!Factory.factories.isEmpty()&&b1) {
                        graphics= Factory.factories.get(Factory.factories.size()-1).factoryGraphics;
                        System.out.println(graphics.scale);
                        System.out.println(FactoryWellGraphics.X_SCALE*graphics.scale);
                        graphics.jPanel.setBounds(b.getX(), b.getY(), (int)(FactoryWellGraphics.X_SCALE*graphics.scale),(int)(FactoryWellGraphics.Y_SCALE*graphics.scale));
                        Container container1=Canvas.frame.getContentPane();
                        container1.remove(b);
                    }

                }
            });
            int x=30;
            if (i>3)
                x=800;
            int y=100+i*160;
            if (i>3)
                y=170+(i-4)*160;
            b.setBounds(x,y,50,50);
            ImageIcon icon=new ImageIcon(Images.getFactoryImage(s));
            b.setIcon(FactoryWellGraphics.resizeIcon(icon,50,50));

            factoryButtons.add(b);
            container.add(b);
        }
        tasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.myClock.setPaused(true);
                Time.setIsPaused(true);
                Menu.getTaskMenuInstance().setVisible(true);
            }
        });
        tasks.setBounds(700,670,100,40);


        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.myClock.setPaused(true);
                Time.setIsPaused(true);
                Menu.getPauseMenuInstance().setVisible(true);
            }
        });
        pause.setIcon(FactoryWellGraphics.resizeIcon(new ImageIcon(Images.pause),60,60
        ));

        pause.setBounds(600,670,60,60);

        coins.setBackground(Color.GREEN);
        coins.setOpaque(true);
        coins.setBounds(850,710,130,40);
        container.add(coins);
        container.add(tasks);
        container.add(pause);
        container.add(Warehouse.getInstance().jButton);
        container.add(jButton);
        container.add(mousePanel);
        container.add(Game.myClock.timeLabel);
        container.add(Well.getInstance().wellGraphics.jPanel);
    }



    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D) g;


        if(Menu.game) {
            paintLand(gg);
            this.pause.repaint();
            this.tasks.repaint();
            Well.getInstance().wellGraphics.jPanel.repaint();
            Truck.getInstance().jButton.repaint();
            Warehouse.getInstance().jButton.repaint();
            paintGrass(gg);
            paintProducts(gg);
            paintDomestics(gg);
            paintWilds(gg);
            paintCats(gg);
            paintHounds(gg);
            Game.myClock.setTimeLabel();
            coins.setText("Coins: "+Game.getCoins());
            coins.repaint();
            //TODO
            //uncomment to see jpanel
            //mousePanel.repaint();
            for (JButton jButton:jButtons){
                jButton.repaint();
            }
            for (JButton jButton:factoryButtons){
                jButton.repaint();
            }
            for (Factory factory:Factory.factories){
                factory.factoryGraphics.jPanel.repaint();
            }
            //frame.repaint();
        }

    }

    static void paintLand(Graphics2D gg)
    {

        gg.setColor(Color.RED);
        gg.drawImage(Images.land,0,0,w,getH(Images.land,w),null);
        int lh = getH(Images.land,w);
        y0 = (int)(h/2 - (double)(lh)/20 - lh/2);
        Well.getInstance().wellGraphics.jPanel.repaint();

    }

    static void paintDomestics(Graphics2D gg)
    {
        for (int i=0; i<Domestic.list.size(); i++)
        {
            //System.out.println("***********");
            Domestic domestic = Domestic.list.get(i);
            BufferedImage image = null;
            if(domestic.type.equals("chicken"))
            {
                domestic.frame = new Frame(24,5);
                domestic.frame.image = Images.chicken[domestic.state-1];
                image = domestic.frame.getFrame(domestic);
                domestic.w = image.getWidth();
                domestic.h= image.getHeight();
            }
            else if(domestic.type.equals("turkey"))
            {
                if(domestic.state == 2)
                    domestic.frame = new Frame(24,6);
                else
                    domestic.frame = new Frame(24,4);
                domestic.frame.image = Images.turkey[domestic.state-1];
                image = domestic.frame.getFrame(domestic);
                domestic.w = image.getWidth();
                domestic.h= image.getHeight();
            }
            else if(domestic.type.equals("buffalo"))
            {
                if(domestic.state == 2||domestic.state==4)
                    domestic.frame = new Frame(24,6);
                else
                    domestic.frame = new Frame(24,4);
                domestic.frame.image = Images.buffalo[domestic.state-1];
                image = domestic.frame.getFrame(domestic);
                domestic.w = image.getWidth();
                domestic.h= image.getHeight();
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
                if(wild.state == 2)
                    wild.frame = new Frame(24,5);
                else if(wild.state == 4)
                    wild.frame = new Frame(24,6);
                else
                    wild.frame = new Frame(24,3);
                wild.frame.image = Images.lion[wild.state-1];
                image = wild.frame.getFrame(wild);
                wild.w = image.getWidth();
                wild.h= image.getHeight();
            }
            else if(wild.type.equals("bear"))
            {
                wild.frame = new Frame(24,4);
                wild.frame.image = Images.bear[wild.state-1];
                image = wild.frame.getFrame(wild);
                wild.w = image.getWidth();
                wild.h= image.getHeight();
            }
            else if(wild.type.equals("tiger"))
            {
                if(wild.state == 2||wild.state==4)
                    wild.frame = new Frame(24,5);
                else
                    wild.frame = new Frame(24,4);
                wild.frame.image = Images.tiger[wild.state-1];
                image = wild.frame.getFrame(wild);
                wild.w = image.getWidth();
                wild.h= image.getHeight();
            }
            BufferedImage image1;
            if (wild.leftCages<wild.cages){
                image1=wild.images[wild.leftCages];
                gg.drawImage(image1,wild.xScale(),wild.yScale(),wild.w,wild.h,null);
            }
            gg.drawImage(image,wild.xScale(),wild.yScale(),wild.w,wild.h,null);
        }
    }

    static void paintCats(Graphics2D gg)
    {
        for (int i=0; i<Cat.list.size(); i++)
        {
            Cat cat = Cat.list.get(i);
            BufferedImage image;
            if(cat.state == 2||cat.state==4)
                cat.frame = new Frame(24,6);
            else
                cat.frame = new Frame(24,4);
            cat.frame.image = Images.cat[cat.state-1];
            image = cat.frame.getFrame(cat);
            cat.w = image.getWidth();
            cat.h= image.getHeight();
            gg.drawImage(image,cat.xScale(),cat.yScale(),cat.w,cat.h,null);
        }
    }

    static void paintHounds(Graphics2D gg)
    {
        for (int i=0; i<Hound.list.size(); i++)
        {
            Hound hound = Hound.list.get(i);
            BufferedImage image;
            hound.frame = new Frame(24,6);
            hound.frame.image = Images.hound[hound.state-1];
            image = hound.frame.getFrame(hound);
            hound.w = image.getWidth();
            hound.h= image.getHeight();
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