import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse {
    private static Warehouse w;
    int capacity;
    int occupied;
    ArrayList<Product> products=new ArrayList<>();
    public HashMap<String,Integer> productIntegerHashMap=new HashMap<>();
    JButton jButton;
    Warehouse(){
        capacity=30;
        jButton=new JButton();
        jButton.setBounds(410,640,120,100);
        jButton.setOpaque(true);
        jButton.setContentAreaFilled(true);
        //jButton.setBackground(new Color(0,0,0,1));
        jButton.setIcon(FactoryWellGraphics.resizeIcon(new ImageIcon(Images.warehouse),120,100));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.myClock.setPaused(true);
                Time.setIsPaused(true);
                TruckMenu.getMenuInstance().setVisible(true);
            }
        });
    }

    public static Warehouse getInstance() {
        if (w==null)
            w=new Warehouse();
        return w;
    }

    public boolean add(Product product){
        if (occupied+product.space<=capacity) {
            //Product.list.remove(product);
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
            if (product.type.equals(product1.type) && num>=number){
                occupied-=product.space;
                productIntegerHashMap.put(product1.type,num-number);
                if (num-number==0) {
                    productIntegerHashMap.remove(product1.type);
                    products.remove(product1);
                    return true;
                }
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
        System.out.println("----Warehouse----");
        System.out.println("Occupied space: "+occupied);
        for (Product product:Warehouse.getInstance().products){
            System.out.println(product.type+" "+productIntegerHashMap.get(product.type));
        }
    }

    public HashMap<String, Integer> getProductIntegerHashMap() {
        return productIntegerHashMap;
    }
}