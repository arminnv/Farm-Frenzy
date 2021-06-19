import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public static String userName="(?i)username \\w+";
    public static String password="(?i)password \\w+";
    public static String login="(?i)log in";
    public static String signup="(?i)signup";
    public static String start="(?i)start \\d+";
    public static String logout="(?i)log out";
    public static String settings="(?i)settings";
    static void enter()
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("log in | signup");
            String st = sc.next().toLowerCase();

            if(st.equals("log"))
            {
                sc.next();
                String name = sc.next();
                String pass = sc.next();
                User user = User.login(name, pass);
                if(user != null)
                {
                    User.current = user;
                    main(user);
                }
            }
            else if(st.equals("signup"))
            {
                String name = sc.next();
                String pass = sc.next();
                User user = User.signup(name, pass);
                if(user != null)
                {
                    main(user);
                }
            }
            else
                System.out.println("Invalid Command");
        }
    }

    static void main(User user)
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.println("start | log out | settings");
            String st = sc.next().toLowerCase();

            if(st.equals("start"))
            {
                int level = sc.nextInt();
                if(user.unlockedLevels >= level)
                {
                    Game.run(Mission.list.get(level-1));
                }
                else
                    System.out.println("level " + level + " is locked");
            }
            else if(st.equals("log"))
            {
                sc.next();
                enter();
                Logger.write('i',"log out successful");
                break;
            }
            else if(st.equals("settings"))
            {

            }
        }
    }
}