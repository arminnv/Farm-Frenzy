public class Output {

    static void show()
    {
        System.out.println("time : " + Time.time);
        System.out.println("coins: "+Game.getCoins());
        showLand();
        showAnimals();
        showProducts();
        showTasks();
        showWarehouse();
        System.out.println("--------------------------------------");
    }

    static void showLand()
    {
        System.out.println("+ - - - - - - +");
        for(int i=5; i>=0; i--)
        {
            System.out.print("| ");
            for (int j=0; j<6; j++)
                System.out.print(Plant.num[j][i] + " ");
            System.out.println("|");
        }
        System.out.println("+ - - - - - - +");
    }

    static void showAnimals()
    {
        //implement show for animals
        //for (Animal animal:Animal.animals)
        //     animal.show
        for (int i=0; i<Domestic.list.size(); i++)
        {
            Domestic domestic = Domestic.list.get(i);
            System.out.println(domestic.type + " " + domestic.health + "% " + domestic.x + " " + domestic.y);
        }

        for (int i=0; i<Wild.list.size(); i++)
        {
            Wild wild = Wild.list.get(i);
            System.out.println(wild.type + " " + wild.leftCages + " " + wild.x + " " + wild.y);
        }

        for (int i=0; i<Cat.list.size(); i++)
        {
            Cat cat = Cat.list.get(i);
            System.out.println(cat.type + " " + cat.x + " " + cat.y);
        }

        for (int i=0; i<Hound.list.size(); i++)
        {
            Hound hound = Hound.list.get(i);
            System.out.println(hound.type + " " + hound.x + " " + hound.y);
        }
    }

    static void showProducts()
    {
        for (int i=0; i<Product.list.size(); i++)
        {
            Product product = Product.list.get(i);
            if(product.collected)
                continue;
            System.out.println(product.type + " " + product.x + " " + product.y);
        }
    }

    static void showTasks()
    {
        for (int i=0; i<Task.list.size(); i++)
        {
            Task task = Task.list.get(i);
            System.out.println(task.name + ": " + task.claimed + "/" + task.goal);
        }
    }
    static void showWarehouse(){
        Warehouse.getInstance().show();
    }

}