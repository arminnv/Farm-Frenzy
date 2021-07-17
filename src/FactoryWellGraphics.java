import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FactoryWellGraphics {
    public static final boolean LEFT_ALIGNMENT=false;
    public static final boolean RIGHT_ALIGNMENT=true;
    public static final int Y_SCALE=46;
    public static final int X_SCALE=65;
    public static final int SPACE_SCALE=3;
    public static final int IMAGE_SCALE=40;
    public static final int BUTTON_W_SCALE=16;
    public static final int BUTTON_H_SCALE=10;
    public static final int PROGRESS_X_SCALE=8;
    public static final int PROGRESS_Y_SCALE=27;
    public static final int PROGRESS_XLOC_SCALE=(BUTTON_H_SCALE-PROGRESS_X_SCALE)/2+SPACE_SCALE;

    JPanel jPanel;
    JButton upgrade;
    JProgressBar jProgressBar;
    JButton mainButton;
    BufferedImage image;
    boolean upgradable;
    FactoryWellGraphics(double scale,int x, int y,boolean upgradable,BufferedImage image, boolean alignment){
        this.upgradable=upgradable;
        this.image=image;
        jPanel=new JPanel();
        jPanel.setOpaque(false);
        jPanel.setLayout(new GroupLayout(jPanel));
        jPanel.setVisible(true);
        if (upgradable){
            upgrade=new JButton("Upgrade");
            if (alignment==LEFT_ALIGNMENT)
                upgrade.setBounds(Scale(scale,X_SCALE-SPACE_SCALE-BUTTON_W_SCALE),Scale(scale,SPACE_SCALE),Scale(scale,BUTTON_W_SCALE),Scale(scale,BUTTON_H_SCALE));
            else//=RIGHT
                upgrade.setBounds(Scale(scale,SPACE_SCALE),Scale(scale,SPACE_SCALE),Scale(scale,BUTTON_W_SCALE),Scale(scale,BUTTON_H_SCALE));
            jPanel.add(upgrade);
        }

        mainButton=new JButton();
        mainButton.setOpaque(false);
        mainButton.setContentAreaFilled(false);
        //TODO
        mainButton.setBorderPainted(true);
        mainButton.setIcon( resizeIcon(new ImageIcon(image),Scale(scale,IMAGE_SCALE),Scale(scale,IMAGE_SCALE)) );
        mainButton.setBounds(Scale(scale,X_SCALE-SPACE_SCALE-IMAGE_SCALE),Scale(scale,SPACE_SCALE),Scale(scale,IMAGE_SCALE),Scale(scale,IMAGE_SCALE));


        jProgressBar=new JProgressBar();
        jProgressBar.setOrientation(SwingConstants.VERTICAL);
        jProgressBar.setStringPainted(true);
        if (alignment==RIGHT_ALIGNMENT)
            jProgressBar.setBounds(Scale(scale,PROGRESS_XLOC_SCALE),Scale(scale,SPACE_SCALE*2+BUTTON_H_SCALE),
                    Scale(scale,PROGRESS_X_SCALE),Scale(scale,PROGRESS_Y_SCALE));
        else
            jProgressBar.setBounds(Scale(scale,X_SCALE-PROGRESS_XLOC_SCALE-PROGRESS_X_SCALE),
                    Scale(scale,SPACE_SCALE*2+BUTTON_H_SCALE),
                    Scale(scale,PROGRESS_X_SCALE),Scale(scale,PROGRESS_Y_SCALE));
        jPanel.add(mainButton);
        jPanel.add(jProgressBar);
        jPanel.setBounds(x,y,(int)scale*X_SCALE,(int)scale*Y_SCALE);
    }
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    private static int Scale(double scale, int number){
        return (int)scale*number;
    }
    public void setUpgrade(boolean upgradable){
        if (upgrade!=null){

            if ( (!upgradable) &&this.upgradable) {
                this.jPanel.remove(upgrade);
            }
            else if ( (!this.upgradable)&&(upgradable) )
                this.jPanel.add(upgrade);
            this.upgradable=upgradable;
        }
    }
}