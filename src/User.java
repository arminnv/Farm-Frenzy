import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User {

    public static ArrayList<User> list = new ArrayList<>();

    static User current;
    String userName;
    String password;
    int unlockedLevels = 1;
    int points;

    User(String name, String pass)
    {
        userName = name;
        password = pass;
        points=0;
    }

    static void load()
    {
        try
        {
            FileReader reader = new FileReader("users.txt");
            Scanner sc = new Scanner(reader);
            String str = "";

            while (sc.hasNextLine())
            {
                str += sc.nextLine();
                if(str.charAt(str.length()-1) == '*')
                {
                    str = str.substring(0, str.length()-1);
                    //reader.close();
                    Gson gson = new Gson();
                    User user = gson.fromJson(str, User.class);
                    User.list.add(user);
                    str = "";
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {}
    }

    static User login(String name, String password)
    {
        for (int i=0; i<User.list.size(); i++)
        {
            if(User.list.get(i).userName.equals(name))
            {
                if(User.list.get(i).password.equals(password))
                {
                    User.current = User.list.get(i);
                    Menu.showMessage('i',"Login was successful");
                    Logger.write('i',"login successful");
                    return User.list.get(i);
                }
                else
                {
                    Menu.showMessage('e',"Incorrect password");
                    System.out.println("wrong password");
                    Logger.write('e',"wrong password");
                    return null;
                }
            }
        }
        Menu.showMessage('e',"Username was not found");
        Logger.write('e',"username not found");
        return null;
    }
    public static int indexOf(String username){
        for (User user:list){
            if (username.equals(user.userName))
                return list.indexOf(user);
        }
        return -1;
    }


    static void levelUp(User user)
    {
        String st = "";
        String s;
        user.points+=Game.mission.reward;
        HashMap<Integer,Integer> map=Game.mission.TimeBonus;
        for (Integer time:map.keySet()){
            if (Time.time<=time){
                user.points+=map.get(time);
            }
        }
        try {
            FileReader reader = new FileReader("users.txt");
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine())
            {
                s = sc.nextLine();
                if(!s.contains(user.userName))
                    st += s;
            }
            reader.close();
        }
        catch (IOException e)
        {}

        try
        {
            FileWriter writer = new FileWriter("users.txt");
            String json = new Gson().toJson(user);
            writer.write(st + json + "*\n");
            writer.close();
            User.list.clear();
            User.load();
        }
        catch (IOException e)
        {
            System.out.println("error");
            Logger.write('e',"error");
        }
    }
}