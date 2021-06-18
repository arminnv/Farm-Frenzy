import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User {

    static ArrayList<User> list = new ArrayList<>();

    static User current;
    String userName;
    String password;
    int unlockedLevels = 1;
    int bonusCoins = 0;

    User(String name, String pass)
    {
        userName = name;
        password = pass;
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
                    System.out.println("login successful");
                    Logger.write('i',"login successful");
                    return User.list.get(i);
                }
                else
                {
                    System.out.println("wrong password");
                    Logger.write('e',"wrong password");
                    return null;
                }
            }
        }
        System.out.println("username not found");
        Logger.write('e',"username not found");
        return null;
    }

    static User signup(String name, String password)
    {
        User newUser = new User(name, password);
        if(!User.list.contains(newUser))
            list.add(newUser);
        else
        {
            System.out.println("username already exists");
            Logger.write('e',"username already exists");
            return null;
        }
        //omit start
        String st = "";
        try
        {

            FileReader reader = new FileReader("users.txt");
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine())
            {
                st += sc.nextLine();
            }
            reader.close();
        }
        catch (IOException e)
        {}
        //omit end
        try
        {
            FileWriter writer = new FileWriter("users.txt");//new FileWriter("users.txt",true);
            String json = new Gson().toJson(newUser);
            writer.write(st + json + "*");
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
            Logger.write('e',"error");
        }
        System.out.println("signup successful");
        Logger.write('i', "signup successful");
        return newUser;
    }

    static void levelUp(User user)
    {
        String st = "";
        String s;
        try
        {
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
            writer.write(st + json + "*");
            writer.close();
            User.list.clear();
            User.load();
        }
        catch (IOException e)
        {
            System.out.println("error");
            Logger.write('e',"error");
        }
        System.out.println("signup successful");
        Logger.write('i', "signup successful");
    }
}
