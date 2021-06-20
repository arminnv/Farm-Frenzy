import java.util.HashMap;

public class Truck {
    private static Truck t;
    public static final int MAX_CAPACITY=15;
    public static final int TOTAL_DURATION=10;
    int timeLeft;
    int capacity;
    boolean loaded;
    int money;
    HashMap<String,Integer> productIntegerHashMap=new HashMap<>();
    HashMap<String,Integer> animalIntegerHashMap=new HashMap<>();
    Truck(){
        capacity=0;
        loaded=false;
        money=0;
        timeLeft=0;
    }
    public static Truck getInstance() {
        if (t==null)
            t=new Truck();
        return t;
    }
    public void TruckUnload(String item){
        if (Product.nameList.contains(item))
            unloadProduct(item);
        else{
            unloadAnimal(item);
        }
    }
    public void TruckLoad(String item){
        if (Truck.getInstance().timeLeft>0){
            System.out.println("Error: Truck is on the way");
            Logger.write('e',"Truck is on the way");
            return;
        }
        if (Product.nameList.contains(item))
            loadProduct(item);
        else{
            loadAnimal(item);
            /*
            if (item.equals("cat"))
                loadAnimal(item,"cat");
            else if (item.equals("hound"))
                loadAnimal(item,"hound");
            else
                loadAnimal(item,"Domestic");//wild animal problem
             */
        }
    }
    private void loadProduct(String type){
        if (Warehouse.getInstance().inquiry(type,1)){
            Product p=new Product(type);
            if (capacity+p.space<=MAX_CAPACITY) {
                capacity+=p.space;
                if (productIntegerHashMap.containsKey(type))
                    productIntegerHashMap.put(type, productIntegerHashMap.get(type)+1);
                else
                    productIntegerHashMap.put(type, 1);
            }
        }
    }
    private void loadAnimal(String name){
        HashMap<String,Integer> map=Animal.animalCount;
        if (map.containsKey(name)){
            Animal animal=new Domestic(name,false);
            if (name.equals("cat"))
                animal=new Cat(false);
            else if (name.equals("hound"))
                animal=new Hound();
            else if (!Domestic.nameList.contains(name)) {
                Logger.write('e',"Invalid item for loading");
                System.out.println("Invalid item for loading");
                return;
            }
            if ( (map.get(name)>0)&&(capacity+animal.space<=MAX_CAPACITY)) {
                capacity+=animal.space;
                if (!animalIntegerHashMap.containsKey(animal.type))
                    animalIntegerHashMap.put(animal.type, 0);
                animalIntegerHashMap.put(animal.type, animalIntegerHashMap.get(animal.type)+1);
                Logger.write('i',animal.type + " loaded");
            }
        }
    }
    private void loadAnimal(String name, String generalType){
        HashMap<String,Integer> map=Animal.animalCount;
        if (map.containsKey(name)){
            Animal animal=new Domestic(name,false);
            if (generalType.equals("cat"))
                animal=new Cat(false);
            else if (generalType.equals("hound"))
                animal=new Hound();
            else if (!generalType.equals("Domestic"))
                return;

            if ( (map.get(name)>0)&&(capacity+animal.space<=MAX_CAPACITY)) {
                capacity+=animal.space;
                if (!animalIntegerHashMap.containsKey(animal.type))
                    animalIntegerHashMap.put(animal.type, 0);
                animalIntegerHashMap.put(animal.type, animalIntegerHashMap.get(animal.type)+1);
                Logger.write('i',animal.type + " loaded");
            }
        }
    }

    private void unloadProduct(String type){
        if (productIntegerHashMap.containsKey(type)){
            Product p=new Product(type);
            if ( productIntegerHashMap.get(type)>0 ) {//&&(capacity+p.space<=MAX_CAPACITY) ??????
                capacity-=p.space;
                productIntegerHashMap.put(type, productIntegerHashMap.get(type)-1);
                Logger.write('i',p.type + " unloaded");
            }
        }
    }

    private void unloadAnimal(String name){
        HashMap<String,Integer> map=animalIntegerHashMap;
        if (map.containsKey(name)){
            Animal animal=new Domestic(name,false);
            if (name.equals("cat"))
                animal=new Cat(false);
            else if (name.equals("hound"))
                animal=new Hound();
            if ( map.get(name)>0 ) { //&&(capacity+animal.space<=MAX_CAPACITY) ??????
                capacity-=animal.space;
                animalIntegerHashMap.put(animal.type, map.get(animal.type)-1);
                Logger.write('i',animal.type + " unloaded");
            }
        }
    }
    public void confirm(){
        if (productIntegerHashMap.isEmpty()&&animalIntegerHashMap.isEmpty()){
            Logger.write('e',"Empty truck cannot leave");
            System.out.println("Empty truck cannot leave");
            return;
        }
        for (String type:productIntegerHashMap.keySet()) {
            Product p=new Product(type);
            Warehouse.getInstance().remove(p,productIntegerHashMap.get(type));
            money+=p.price*productIntegerHashMap.get(type);
        }
        for (String name: animalIntegerHashMap.keySet()) {
            Animal animal=new Domestic(name,false);
            if (name.equals("cat"))
                animal=new Cat(false);
            else if (name.equals("hound"))
                animal=new Hound();
            for (int i = 0; i < animalIntegerHashMap.get(name); i++) {
                Animal.removeFromMap(name);
                if(name.equals("cat"))
                    Cat.list.remove(0);
                else if(name.equals("hound"))
                    Hound.list.remove(0);
                else {
                    for (int j=0; j<Domestic.list.size(); j++) {
                        if(Domestic.list.get(j).type.equals(name)) {
                            Domestic.list.remove(j);
                            break;
                        }
                    }
                }
            }
            money+=animal.price*animalIntegerHashMap.get(name);
        }
        timeLeft=TOTAL_DURATION;
        animalIntegerHashMap.clear();
        productIntegerHashMap.clear();
        capacity=0;
        Logger.write('i', "truck left");
        System.out.println("Truck left");
    }
    public void update(){
        if (timeLeft==0){
            Game.addCoins(money);
            money=0;
            return;
        }
        timeLeft--;
    }
}
