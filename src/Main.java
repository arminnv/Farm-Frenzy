public class Main {
    public static void main(String[] args)
    {
        Canvas canvas = new Canvas();
        canvas.setFrame();
        Mission.write();
        User.load();
        Mission.load();
        Menu.enter();

    }
}