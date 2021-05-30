import java.lang.reflect.Type;

public class Main {
    public static void main(String args[])
    {
        System.out.println("Arman");
        System.out.println("Hello");
        System.out.println("This a test using Intellij");
        Type type1=Icecream.class;
        Type type2=Feather.class;
        Type type3=Icecream.class;
        Product p=new Egg();
        Type type4= p.getClass();
        System.out.println(type2);
        System.out.println(type1);
        System.out.println(type4);
        if (type1.equals(type3))
            System.out.println(1);
    }
}
