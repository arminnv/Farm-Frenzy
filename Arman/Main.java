public class Main {
    public static boolean ifRun=false;
    public static boolean ifMap=false;
    public static boolean ifLogout=false;
    public static boolean ifRestart=false;
    public static int level=1;
    public static void main(String[] args)
    {
        Images.load();
        Mission.write();
        User.load();
        Mission.load();
        //User.current = User.list.get(0);
        Menu.getLoginMenuInstance().setVisible(true);
        while (true) {
            while (!ifRun) {
                System.out.print("");
            }
            Game.run(Mission.list.get(level-1));
        }
    }
}