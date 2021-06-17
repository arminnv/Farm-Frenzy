import java.util.ArrayList;

public class Task {

    static ArrayList<Task> list = new ArrayList<>();

    String name;
    int claimed = 0;
    int goal;
    boolean complete = false;

    Task(String Name, int Goal)
    {
        name = Name;
        goal = Goal;
    }

    static void add(Mission mission)
    {
        Task.list.addAll(mission.tasks);
    }

    static void claim(String st)
    {
        for (int i=0; i<list.size(); i++)
        {
            if(st.equals(list.get(i).name) && !list.get(i).complete)
                list.get(i).claimed++;
        }
    }

    static boolean check()
    {
        boolean result  = true;
        for (int i=0; i<list.size(); i++)
        {
            if (list.get(i).claimed >= list.get(i).goal)
                list.get(i).complete = true;
            else
                result = false;
        }
        if(result)
        User.current.unlockedLevels++;

        return result;
    }
}
