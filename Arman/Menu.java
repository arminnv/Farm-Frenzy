import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {

    static boolean game = false;
    public static JDialog loginMenu;
    public static JDialog mainMenu;
    private static int login;
    public static JDialog getLoginMenuInstance(){
        if (loginMenu==null) {
            loginMenu = new JDialog();
            loginMenu.setTitle("Login");
            loginMenu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            Container container=loginMenu.getContentPane();
            container.setBackground(Color.orange);
            LayoutManager layoutManager=new GroupLayout(container);
            loginMenu.setLayout(layoutManager);
            JButton signup=new JButton("Sign up");
            JButton login=new JButton("Login");
            JButton exit=new JButton("Exit");

            //login.setIcon( FactoryWellGraphics.resizeIcon(new ImageIcon(Images.bakery),100,50) );
            signup.setBounds(10,10,100,50);
            login.setBounds(160,10,100,50);
            exit.setBounds(310,10,100,50);
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username=JOptionPane.showInputDialog("Enter username");
                    String password=JOptionPane.showInputDialog("Enter password");
                    User user = User.login(username, password);
                    if(user != null) {
                        User.current = user;
                        loginMenu.setVisible(false);


                        getMainMenuInstance().setVisible(true);
                    }
                }
            });
            signup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username= JOptionPane.showInputDialog("Enter new username");//null problem on closing
                    if (username==null)
                        return;
                    while (User.indexOf(username)!=-1){
                        JOptionPane.showMessageDialog(new JFrame(),"User already exists","Error",JOptionPane.ERROR_MESSAGE);
                        username= JOptionPane.showInputDialog("Enter username");
                        if (username==null)
                            return;
                    }
                    String password=JOptionPane.showInputDialog("Enter password");
                    if (password==null)
                        return;
                    User newUser=new User(username, password);
                    User.list.add(newUser);
                    Menu.showMessage('i',"Successful signup");
                    Logger.write('i', "signup successful");
                    try
                    {
                        FileWriter writer = new FileWriter("users.txt",true);
                        String json = new Gson().toJson(newUser);
                        writer.write(json + "*\n");
                        writer.close();
                    }
                    catch (IOException exception)
                    {
                        Menu.showMessage('e',"Error in saving the new user");
                        Logger.write('e',"error");
                    }
            }});
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                    System.exit(0);
                }
            });
            loginMenu.setSize(430,110);
            container.add(signup);
            container.add(login);
            container.add(exit);
            //TODO
        }
        return loginMenu;
    }

    public static JDialog getMainMenuInstance() {
        if (mainMenu==null){
            mainMenu=new JDialog();
            mainMenu.setTitle("Welcome "+User.current.userName);
            mainMenu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            Container container=mainMenu.getContentPane();
            LayoutManager layoutManager=new GroupLayout(container);
            mainMenu.setLayout(layoutManager);
            JButton exit=new JButton("Exit");
            JButton logout=new JButton("Log out");
            int menuH=400;
            int menuW=630;
            ArrayList<JButton> jButtons=new ArrayList<>();
            for (int i=0;i<Mission.list.size();i++){
                JButton b=new JButton(String.valueOf(i+1));
                b.setName(String.valueOf(i+1));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(b.getName());
                        int level=Integer.parseInt(b.getName());
                        if(User.current.unlockedLevels >= level) {
                            game = true;
                            mainMenu.setVisible(false);
                            //TODO
                            Menu.game=true;
                            Main.ifRun=true;
                        }
                        else
                            Menu.showMessage('e',"Level " + level + " is locked");

                    }
                });
                //TODO
                b.setBounds(10+50*i,10,50,30);
                jButtons.add(b);
                container.add(b);
            }
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                    System.exit(0);
                }
            });
            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainMenu.setVisible(false);
                    Menu.showMessage('i',"Logout was successful");
                    Logger.write('i',"log out successful");
                    loginMenu.setVisible(true);
                }
            });
            exit.setBounds(10,60,80,30);
            logout.setBounds(100,60,80,30);

            container.add(exit);
            container.add(logout);
            mainMenu.setSize(menuW,menuH);
        }
        return mainMenu;
    }
    public static void showMessage(char c,String message){
        String s="";
        int num= JOptionPane.INFORMATION_MESSAGE;
        if (c=='e') {
            s = "Error";
            num = JOptionPane.ERROR_MESSAGE;
        }
        else if (c=='r'){
            s="Results";
            num=JOptionPane.PLAIN_MESSAGE;
        }
        else
            s="Notice";
        JFrame jFrame=new JFrame(s);
        jFrame.setSize(300,300);
        JOptionPane.showMessageDialog(jFrame,message,s,num);
    }
}