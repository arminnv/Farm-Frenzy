public class Main {
    public static void main(String[] args)
    {
        Canvas canvas = new Canvas();
        canvas.setFrame();
        Mission.write();
        User.load();
        Mission.load();
        Menu.game = true;
        User.current = User.list.get(0);
        Game.run(Mission.list.get(0));
        Menu.enter();

    }
}