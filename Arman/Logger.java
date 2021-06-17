import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Logger {

    static void write(char ch, String st)
    {
        String s = "";
        String str = "";

        if(ch  == 'i')
            str = "[Info], ";
        else if(ch == 'e')
            str = "[Error], ";
        else if(ch == 'a')
            str = "[Alarm], ";
        else
            str = "[Info], ";

        st = str + getDate() + ", " + st;

        //omit start
        try
        {
            FileReader reader = new FileReader("log.txt");
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine())
            {
                s += sc.nextLine() + "\n";
            }
            reader.close();
        }
        catch (IOException e)
        {}
        //omit end
        try
        {
            FileWriter writer = new FileWriter("log.txt");//new FileWriter("log.txt",true)
            writer.write(s + "\n" +  st);
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
