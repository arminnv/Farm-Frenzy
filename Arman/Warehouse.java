import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse {
    private static Warehouse w;
    int capacity;
    int occupied;
    ArrayList<Product> products=new ArrayList<>();
    private HashMap<String,Integer> productIntegerHashMap=new HashMap<>();
    Warehouse(){
        capacity=30;
    }

    public static Warehouse getInstance() {
        if (w==null)
            w=new Warehouse();
        return w;
    }

    public boolean add(Product product){//must be checked for bugs
        if (occupied+product.space<capacity) {
            occupied += product.space;
            for (Product product1 : products) {
                if (product1.type.equals(product.type)) {
                    productIntegerHashMap.put(product1.type, productIntegerHashMap.get(product1.type) + 1);
                    return true;
                }
            }
            products.add(product);
            productIntegerHashMap.put(product.type, 1);
            return true;
        }
        return false;
    }
    public boolean remove(Product product, int number){
        for (Product product1:products){
            int num=productIntegerHashMap.get(product1.type);
            if (product.type.equals(product1.type) && num>number){
                productIntegerHashMap.put(product1.type,num-number);
                return true;
            }
        }
        return false;
    }
    public boolean inquiry(String type,int number){
        if (!productIntegerHashMap.containsKey(type))
            return false;
        return productIntegerHashMap.get(type) >= number;
    }
    public void show(){
        for (Product product:Warehouse.getInstance().products){
            System.out.println(product.type+" "+productIntegerHashMap.get(product.type));
        }
    }
}
