import java.util.Scanner;

public class Menu {

    static boolean game = false;

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
            else if (st.equals("exit"))
                break;
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
                    game = true;
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
            else if(st.equals("settings")) {
                System.out.println("⚙ settings ⚙\n" +
                        "VOLUME   - ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒ +");
            }
        }
    }
}