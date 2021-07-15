public class Main {
    public static boolean ifRun=false;
    public static void main(String[] args)
    {
        Images.load();
        Mission.write();
        User.load();
        Mission.load();
        User.current = User.list.get(0);
        Menu.getLoginMenuInstance().setVisible(true);
        while (true) {
            while (!ifRun) {
                System.out.print("");
            }
            Game.run(Mission.list.get(0));
        }
    }
}