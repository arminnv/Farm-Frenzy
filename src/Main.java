public class Main {
    public static void main(String args[])
    {
        User.load();
        Mission.load();
        //Game.run(Mission.list.get(0));
        Menu.enter();
    }
}
