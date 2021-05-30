import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
class zombie
{
    static final int MAX_HEALTH = 120;
    static int health;
    static int row;
    static int col;

    public zombie(int row,int col)
    {
        zombie.row=row;
        zombie.col=col;
        Random rand=new Random();
        zombie.health = rand.nextInt(MAX_HEALTH)+1;
    }
    public zombie(int row,int col,int health)
    {
        zombie.row=row;
        zombie.col=col;
        zombie.health = health;
    }
}

class bomb
{
    //static String [] types={"common" ,"rare" ,"super"};
    static String type;
    static int price;
    static int damage;
    static int row;
    static int col;

    public bomb(int row,int col)
    {
       bomb.row=row;
       bomb.col=col;
       bomb.type="common";
        bomb.price=25;
        bomb.damage=25;
    }

    public bomb(int row,int col,String type)
    {
        bomb.row=row;
        bomb.col=col;
        if(type.equals("common"))
        {
            bomb.price=25;
            bomb.damage=25;
            bomb.type="common";
        }
        else if(type.equals("rare"))
        {
            bomb.price=75;
            bomb.damage=75;
            bomb.type="rare";
        }
        else if(type.equals("super"))
        {
            bomb.price=150;
            bomb.damage=150;
            bomb.type="super";
        }
        else if(type.equals("n"))
        {
            bomb.price=0;
            bomb.damage=0;
            bomb.type="n";
        }
    }
}

class inputprocessor
{
    static void run(Manager manager,ArrayList<zombie> zombies,ArrayList<bomb> bombs)
    {
        Scanner sc = new Scanner(System.in);
        String s,s2;
        int n;
        int r;
        int c;
        int chk=0;
        int game=1;
        while(true)
        {
            System.out.println("Enter your command : ");
            s = sc.nextLine();
            //System.out.println(s+"*");
            char ch=s.charAt(0);
            //System.out.println(ch+"*");
            if(ch=='e')
                break;
            else if(ch=='p')
            {
                if(s.charAt(s.length()-1)<='9'&&s.charAt(s.length()-1)>='0')
                {
                    if(manager.playercoins<=25)
                        break;
                    //System.out.println(1);
                    //System.out.println(1);
                    //System.out.println(s2+"*");
                    s2=s.substring(9);
                    System.out.println(s2.substring(0,s2.indexOf(' '))+"*");
                    System.out.println(s2.substring(s2.indexOf(' ')+1)+"+");

                    r=Integer.parseInt(s2.substring(0,s2.indexOf(' ')));
                    c=Integer.parseInt(s2.substring(s2.indexOf(' ')+1));
                    bomb bmb = new bomb(r,c);
                    bombs.add(bmb);
                    manager.playercoins-=bomb.price;
                }
                else
                {
                    int a=1;
                    ch=s.charAt(s.length()-1);
                    s2=s.substring(9,s.length()-5);
                    r=Integer.parseInt(s2.substring(0,s2.indexOf(' ')));
                    c=Integer.parseInt(s2.substring(s2.indexOf(' ')+1));
                    if(ch=='n')
                    {
                        s="common";
                        if(manager.playercoins<25)
                            a=0;
                    }
                    else if(ch=='e')
                    {
                        s="rare";
                        if(manager.playercoins<75)
                            a=0;

                    }
                    else if(ch=='r')
                    {
                        s="super";
                        if(manager.playercoins<150)
                            a=0;
                    }
                    if(a==1)
                    {
                    bomb bmb = new bomb(r,c,s);
                    bombs.add(bmb);
                    manager.playercoins-=bomb.price;
                    }
                    a=1;
                }
            }
            else if(ch=='c')
            {
                s2=s.substring(13);
                r=Integer.parseInt(s2.substring(0,s2.indexOf(' ')));
                c=Integer.parseInt(s2.substring(s2.indexOf(' ')+1));
                for(int i=0;i<zombies.size();i++)
                {
                    if(zombies.get(i).row==r&&zombies.get(i).col==c)
                    {
                        System.out.println("row : "+r+" column : "+c+" health : "+zombies.get(i).health);
                        chk=1;
                        break;
                    }
                }
                if(chk==0)
                    System.out.println("zombie not found");
                chk=0;

            }
            else if(ch=='r')
            {
                Random ran = new Random();
                n=ran.nextInt(9);
                if(n<5)
                    manager.playercoins+=50;
            }

            for(int i=0;i< bombs.size();i++)
            {
                if(bombs.get(i).damage>0)
                {
                    String str=bombs.get(i).type;
                    if(str.equals("common"))
                    {
                        ch='c';
                        manager.map[bombs.get(i).row-1][bombs.get(i).col-1]=2;
                    }
                    else if(str.equals("rare"))
                    {
                        ch='r';
                        manager.map[bombs.get(i).row-1][bombs.get(i).col-1]=3;
                    }
                    else if(str.equals("super"))
                    {
                        ch='s';
                        manager.map[bombs.get(i).row-1][bombs.get(i).col-1]=4;
                    }

                }
            }
            for(int i=0;i<zombies.size();i++)
            {
                if(zombies.get(i).health>0)
                {
                    zombie z=new zombie(zombies.get(i).row-1,zombies.get(i).col,zombies.get(i).health);
                    zombies.set(i,z);
                    manager.map[zombies.get(i).row-1][zombies.get(i).col-1]=1;
                    manager.map[zombies.get(i).row][zombies.get(i).col-1]=0;
                    System.out.println(zombies.get(i).row);

                    for(int j=0;j<bombs.size();j++)
                    {
                        if(bombs.get(j).row==zombies.get(i).row&&bombs.get(j).col==zombies.get(i).col)
                        {
                            z =new zombie(zombies.get(i).row,zombies.get(i).col,zombies.get(i).health-bombs.get(j).damage);
                            zombies.set(i,z);
                            bomb bm = new bomb(1,1,"n");
                            bombs.set(j,bm);
                            manager.map[bombs.get(j).row-1][bombs.get(j).col-1]=0;
                            break;
                        }
                    }
                    if(zombies.get(i).health>0)
                    {
                        manager.map[zombies.get(i).row-1][zombies.get(i).col-1]=1;
                    if(zombies.get(i).row<1)
                    {
                        game =0;
                        System.out.println("game over");
                        break;
                    }
                    }
                    else
                    {
                        manager.playercoins+=15;
                        manager.zombiescounter--;
                        manager.map[zombies.get(i).row-1][zombies.get(i).col-1]=0;
                    }
                }
            }

            Random u=new Random();
            n= u.nextInt(9);
            if(n<3)
            {
                n=u.nextInt(manager.MAX_COL)+1;
                zombie z=new zombie(manager.MAX_ROW,n);
                zombies.add(z);
                manager.zombiescounter++;
                manager.map[zombies.get(zombies.size()-1).row-1][zombies.get(zombies.size()-1).col-1]=1;
            }


            System.out.println("*         *         *         *");
                for(int j=0;j<manager.MAX_ROW+2;j++)
                    System.out.print("-");
                System.out.println();
            for(int j=0;j<manager.MAX_COL;j++)
            {
                System.out.print(j+1+":");
                for(int i=0;i<manager.MAX_ROW;i++)
                {
                    n=manager.map[i][j];
                    if(n==0)
                        ch=' ';
                    else if(n==1)
                        ch='<';
                    else if(n==2)
                        ch='c';
                    else if(n==3)
                        ch='r';
                    else if(n==4)
                        ch='s';
                    System.out.print(ch);
                }
                System.out.print(":"+(j+1));
                System.out.println();
            }
            for(int j=0;j<manager.MAX_ROW+2;j++)
                System.out.print("-");
            System.out.println();
            System.out.println("you have "+manager.playercoins+" coins");
            System.out.println("Remaining zombies = "+manager.zombiescounter);
        }
    }
}

class Manager
{
static String playername;
static int playercoins;
static ArrayList <zombie> zombies=new ArrayList<zombie>();
static ArrayList <bomb> bombs=new ArrayList<bomb>();
static int [][] map;
static int MAX_ROW;
static int MAX_COL;
static String status;
static Random random;
static int MAX_ZOMBIES;
static int zombiescounter;

public Manager(String playername,int playercoins,int MAX_ROW,int MAX_COL,int MAX_ZOMBIES)
{
    Manager.playername=playername;
    Manager.playercoins=playercoins;
   // Manager.zombies=zombies;
   // Manager.bombs=bombs;
   // Manager.map=map;
    Manager.MAX_ROW=MAX_ROW;;
    Manager.MAX_COL=MAX_COL;
   // Manager.status=status;
   // Manager.random=random;
    Manager.MAX_ZOMBIES=MAX_ZOMBIES;
   // Manager.zombiescounter=zombiescounter;
}
}

public class quiz2 {

    public void tackDamge(int x)
{
    zombie.health-=x;
}

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter name : ");
        String st = sc.next();
        System.out.println("Enter rows :");
        int R= sc.nextInt();
        System.out.println("Enter columns :");
        int C=sc.nextInt();
        Manager manager = new Manager(st,500,R,C,20);
        manager.map = new int[R][C];
        inputprocessor.run(manager,manager.zombies,manager.bombs);



    }
}
