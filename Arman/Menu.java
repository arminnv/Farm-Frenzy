import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
    public static JDialog pauseMenu;
    public static JFrame taskMenu;
    public static JTable tasks;
    private static int login;
    public static JDialog getLoginMenuInstance(){
        if (loginMenu==null) {
            int h=510;
            int w=809;
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
            JLabel jLabel=new JLabel();

            jLabel.setIcon(FactoryWellGraphics.resizeIcon(new ImageIcon(Images.login_back),800,400));
            jLabel.setBounds(0,0,800,400);
            jLabel.setAlignmentY(0);
            //login.setIcon( FactoryWellGraphics.resizeIcon(new ImageIcon(Images.bakery),100,50) );
            signup.setBounds(w/2-220,410,100,50);
            login.setBounds(w/2+150-220,410,100,50);
            exit.setBounds(w/2+300-220,410,100,50);
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
            loginMenu.setSize(w,h);
            container.add(signup);
            container.add(login);
            container.add(exit);
            container.add(jLabel);
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
            int menuH=800;
            int menuW=1000;
            JLabel jLabel=new JLabel();
            jLabel.setBounds(0,0,menuW,menuH);
            jLabel.setIcon(FactoryWellGraphics.resizeIcon(new ImageIcon(Images.main_back),1000,800));
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
                            Main.level=level;
                            Menu.game=true;
                            Main.ifRun=true;
                        }
                        else
                            Menu.showMessage('e',"Level " + level + " is locked");

                    }
                });
                b.setBounds(menuW/7+150*i,menuH/3,100,60);
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
            exit.setBounds(menuW/2-250,menuH/2,80,50);
            logout.setBounds(menuW/2+150,menuH/2,80,50);

            container.add(exit);
            container.add(logout);
            container.add(jLabel);
            mainMenu.setSize(menuW,menuH);
        }
        return mainMenu;
    }
    public static JDialog getPauseMenuInstance(){
        if (pauseMenu==null) {
            int w=200;
            int h=550;
            pauseMenu = new JDialog();
            pauseMenu.setTitle("");
            pauseMenu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            Container container = pauseMenu.getContentPane();
            LayoutManager layoutManager = new GroupLayout(container);
            pauseMenu.setLayout(layoutManager);
            container.setBackground(Color.orange);

            JButton myContinue=new JButton("Continue");
            JButton map=new JButton("Map");
            JButton restart=new JButton("Restart");
            JButton logout=new JButton("Log out");
            JButton exit=new JButton("Exit");

            myContinue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game.myClock.setPaused(false);
                    pauseMenu.setVisible(false);
                    Time.setIsPaused(false);
                }
            });
            restart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
            map.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                    System.exit(0);
                }
            });
            pauseMenu.setSize(w,h);
            myContinue.setBounds(45,45,100,50);
            restart.setBounds(45,130,100,50);
            map.setBounds(45,225,100,50);
            logout.setBounds(45,320,100,50);
            exit.setBounds(45,415,100,50);

            container.add(myContinue);
            container.add(restart);
            container.add(map);
            container.add(logout);
            container.add(exit);
        }
        return pauseMenu;
    }
    public static JFrame getTaskMenuInstance(){
        int w=1000;
        int h=300;
        if (taskMenu==null){
            taskMenu=new JFrame("Tasks");
            taskMenu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            tasks=new JTable();
            JScrollPane jScrollPane=new JScrollPane(tasks);
            tasks.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            Container container = taskMenu.getContentPane();
            LayoutManager layoutManager = new GroupLayout(container);
            taskMenu.setLayout(layoutManager);
            container.setBackground(Color.orange);
            JButton myContinue=new JButton("Continue");
            myContinue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game.myClock.setPaused(false);
                    taskMenu.setVisible(false);
                    Time.setIsPaused(false);
                }
            });
            jScrollPane.setBounds(0,0,w-10,100);
            myContinue.setBounds(w/2-70,200,100,50);
            container.add(myContinue);
            container.add(jScrollPane);
            taskMenu.setSize(w,h);


        }
        showTasksTable(tasks);
        return taskMenu;
    }
    public static void showTasksTable(JTable jTable){

        jTable.removeAll();
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.setColumnSelectionAllowed(false);
        jTable.setModel(new DefaultTableModel(0,Task.list.size()+1));
        DefaultTableModel model=(DefaultTableModel) jTable.getModel();
        TableColumnModel columnModel=jTable.getColumnModel();
        Object[] completed=new Object[Task.list.size()+1];
        Object[] goals=new Object[Task.list.size()+1];
        completed[0]="Completed";
        goals[0]="Goal";
        columnModel.getColumn(0).setHeaderValue("Task name");
        for (int i=0;i<Task.list.size();i++){
            columnModel.getColumn(i+1).setHeaderValue(Task.list.get(i).name);
            completed[i+1]=Task.list.get(i).claimed;
            goals[i+1]=Task.list.get(i).goal;
        }
        model.addRow(completed);
        model.addRow(goals);
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