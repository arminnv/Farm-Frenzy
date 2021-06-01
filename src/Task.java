import java.util.ArrayList;

public class Task {

    static ArrayList<Task> list = new ArrayList<>();

    String name;
    int claimed = 0;
    int goal;

    Task(String Name, int Goal)
    {
        name = Name;
        goal = Goal;
    }

    static void add(String name, int goal)
    {
        Task.list.add(new Task(name, goal));
    }
}
