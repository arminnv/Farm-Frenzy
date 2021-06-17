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
            if (list.get(i).claimed >= list.get(i).goal)
                list.get(i).complete = true;
            if(st.equals(list.get(i).name) && !list.get(i).complete)
                list.get(i).claimed++;
        }
    }

    static boolean check(int level)
    {
        boolean result  = true;
        for (int i=0; i<list.size(); i++)
        {
           if(!list.get(i).complete)
           {
               result = false;
               break;
           }
        }
        if(result)
        {
        User.current.unlockedLevels = max(User.current.unlockedLevels, level + 1);
        User.levelUp(User.current);
        System.out.println("****** level complete ******");
        Logger.write('i',"level complete");
        clear();
        }

        return result;
    }

    static void clear()
    {
        Domestic.list.clear();
        Cat.list.clear();
        Hound.list.clear();
        Wild.list.clear();
        Product.list.clear();
        Task.list.clear();
        for (int i=0; i<6; i++)
            for (int j=0; j<6; j++)
                Plant.num[i][j] = 0;

    }

    static int max(int a, int b)
    {
        if(a>b) return a;
        else return b;
    }
}
