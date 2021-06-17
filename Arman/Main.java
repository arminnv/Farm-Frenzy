import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String args[])
    {
        LocalDateTime d=LocalDateTime.now();
        d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println(d.toString());
        Mission.write();
        Logger.delete();
        User.load();
        Mission.load();
        //User.current = Menu.enter();
        Game.run(Mission.list.get(0));
    }
}
