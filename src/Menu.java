import java.util.Locale;
import java.util.Scanner;

public class Menu {

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
                    main(user);
                    break;
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
                    break;
                }
            }
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
                    Task.add(Mission.list.get(level-1));
                    Game.run(Mission.list.get(level-1));
                }
                else
                    System.out.println("level " + level + " is locked");
            }
            else if(st.equals("log"))
            {
                sc.next();
                enter();
                break;
            }
            else if(st.equals("settings"))
            {

            }
        }
    }
}
