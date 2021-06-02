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

    static void add(Mission mission)
    {
        Task.list.addAll(mission.tasks);
    }
}
