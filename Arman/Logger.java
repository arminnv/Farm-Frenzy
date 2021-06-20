import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Logger {
    public static final File logFile=new File("log.txt");
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
            FileReader reader = new FileReader(logFile);
            Scanner sc = new Scanner(reader);
            header+=sc.nextLine()+"\n";
            sc.nextLine();
            sc.nextLine();
            while (sc.hasNextLine()) {
                s += sc.nextLine() + "\n";
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println(e.toString());
            LocalDateTime d=LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            header+= d.toLocalDate().toString()+" "+d.toLocalTime().toString()+"\n";
        }
        finally {
            header+="Latest change by :";
            if (User.current!=null)
                header+=User.current.userName+"\n";
            else
                header+="No user exists\n";
            LocalDateTime d=LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            header+= d.toLocalDate().toString()+" "+d.toLocalTime().toString()+"\n";
        }
        try
        {
            FileWriter writer = new FileWriter(logFile);
            if (!s.equals(""))
                writer.write(header +s + "\n" +  st);
            else
                writer.write(header+st);
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
    }

    static String getDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
