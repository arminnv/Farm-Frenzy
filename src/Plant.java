public class Plant {

    static int[][] num = new int[6][6];

    public static void warn(){
        int a=0;
        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++){
                a+=num[i][j];
            }
        }
        if (a==0) {
            System.out.println("Warning: no grass");
            Logger.write('a', "No grass");
        }
    }
}