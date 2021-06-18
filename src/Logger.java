import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Logger {

    static void write(char ch, String st)
    {
        String s = "";
        String tag = "";
        String header="";

        if(ch  == 'i')
            tag = "[Info], ";
        else if(ch == 'e')
            tag = "[Error], ";
        else if(ch == 'a')
            tag = "[Alarm], ";
        else
            tag = "[Info], ";

        st = tag + getDate() + ", " + st;
        try
        {
            FileReader reader = new FileReader("log.txt");
            Scanner sc = new Scanner(reader);
            header+=sc.nextLine();
            sc.nextLine();
            sc.nextLine();
            while (sc.hasNextLine()) {
                s += sc.nextLine() + "\n";
            }
            reader.close();
        }
        catch (IOException e) {
            header+= LocalDateTime.now().toString()+"\n";
        }
        finally {
            header+="Latest change by :"+User.current.userName;
            header+=" " + LocalDateTime.now().toString()+"\n";
        }
        try
        {
            FileWriter writer = new FileWriter("log.txt");
            writer.write(header+ "\n" +s + "\n" +  st);
            //writer.write(st);
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
    }
    //LocalDateTime.now().d.toString());
    //
    static String getDate()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return  strDate;
    }

    static void delete()
    {
        File file = new File("log.txt");
        file.delete();
    }
}