public class Output {

    static void show()
    {
        System.out.println(Time.time);
        showLand();
        showAnimals();
        showProducts();
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

        for (int i=0; i<Domestic.list.size(); i++)
        {
            Pet pet = Pet.list.get(i);
            System.out.println(pet.type + " " + pet.x + " " + pet.y);
        }
    }

    static  void showProducts()
    {
        for (int i=0; i<Product.list.size(); i++)
        {
            Product product = Product.list.get(i);
            if(product.collected)
                continue;
            System.out.println(product.type + " " + product.x + " " + product.y);
        }
    }

}
