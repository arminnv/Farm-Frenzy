public class Main {
    public static void main(String args[])
    {
        Mission.write();
        Logger.delete();
        User.load();
        Mission.load();
        Menu.enter();
        //Game.run(Mission.list.get(0));
    }
}
