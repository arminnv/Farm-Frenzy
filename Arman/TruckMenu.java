import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ChronoUnit;

public class TruckMenu {
    private static JTable animalTable;
    private static JTable productTable;
    private static JTable orderTable;
    private static JButton confirm;
    private static JButton addAnimal;
    private static JButton addProduct;
    private static JButton Unload;
    private static JButton UnloadAll;
    private static JLabel capacity;
    private static JLabel money;
    private static JDialog truckMenu;
    private static int H=700;
    private static int W=955;
    public static final int ANIMAL=1;
    public static final int PRODUCT=2;
    public static final int ORDER=3;
    public static JDialog getMenuInstance(){
        if (truckMenu==null){

            truckMenu = new JDialog();
            truckMenu.setTitle("");
            truckMenu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            Container container=truckMenu.getContentPane();
            LayoutManager layoutManager=new GroupLayout(container);
            container.setBackground(Color.GREEN);
            truckMenu.setLayout(layoutManager);

            animalTable=new JTable();
            productTable=new JTable();
            orderTable=new JTable();
            animalTable.setColumnSelectionAllowed(false);
            orderTable.setColumnSelectionAllowed(false);
            productTable.setColumnSelectionAllowed(false);
            animalTable.setModel(new DefaultTableModel(0,3));
            productTable.setModel(new DefaultTableModel(0,3));
            orderTable.setModel(new DefaultTableModel(0,3));

            orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            animalTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            animalTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            TableColumnModel columnModel1=productTable.getColumnModel();
            columnModel1.getColumn(0).setHeaderValue("Name");
            columnModel1.getColumn(1).setHeaderValue("Quantity");
            columnModel1.getColumn(2).setHeaderValue("Price");

            TableColumnModel columnModel2=animalTable.getColumnModel();
            columnModel2.getColumn(0).setHeaderValue("Name");
            columnModel2.getColumn(1).setHeaderValue("Quantity");
            columnModel2.getColumn(2).setHeaderValue("Price");

            TableColumnModel columnModel3=orderTable.getColumnModel();
            columnModel3.getColumn(0).setHeaderValue("Name");
            columnModel3.getColumn(1).setHeaderValue("Quantity");
            columnModel3.getColumn(2).setHeaderValue("Price");

            JScrollPane jScrollPane1=new JScrollPane(animalTable);
            JScrollPane jScrollPane2=new JScrollPane(productTable);
            JScrollPane jScrollPane3=new JScrollPane(orderTable);
            addAnimal=new JButton("Add animal");
            addProduct=new JButton("Add product");
            Unload=new JButton("Unload item");
            UnloadAll=new JButton("Unload All");
            confirm=new JButton("Confirm");

            capacity=new JLabel();
            money=new JLabel();

            capacity.setOpaque(true);
            money.setOpaque(true);
            capacity.setBackground(Color.white);
            money.setBackground(Color.white);


            addAnimal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String g=animalTable.getValueAt(animalTable.getSelectedRow(), 0).toString();
                        Truck.getInstance().TruckLoad(g);
                        capacity.setText(String.valueOf(Truck.getInstance().capacity));
                        money.setText(String.valueOf(Truck.getInstance().money));
                        showTable(orderTable,ORDER);
                        showTable(animalTable,ANIMAL);
                    }catch (Exception exception){
                        Menu.showMessage('e',"No animal is selected");
                    }
                }
            });
            addProduct.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String g=productTable.getValueAt(productTable.getSelectedRow(), 0).toString();
                        Truck.getInstance().TruckLoad(g);
                        capacity.setText(String.valueOf(Truck.getInstance().capacity));
                        money.setText(String.valueOf(Truck.getInstance().money));
                        showTable(orderTable,ORDER);
                        showTable(productTable,PRODUCT);
                    }catch (Exception exception){
                        Menu.showMessage('e',"No product is selected");
                    }
                }
            });
            Unload.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String g=orderTable.getValueAt(orderTable.getSelectedRow(), 0).toString();
                        Truck.getInstance().TruckUnload(g);
                        capacity.setText(String.valueOf(Truck.getInstance().capacity));
                        money.setText(String.valueOf(Truck.getInstance().money));
                        showTable(productTable,PRODUCT);
                        showTable(animalTable,ANIMAL);
                        showTable(orderTable,ORDER);
                        Menu.showMessage('i',"Unload successful");
                    }catch (Exception exception){
                        Menu.showMessage('e',"No order is selected");
                    }
                }
            });
            UnloadAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Truck.getInstance().unLoadAll();
                    capacity.setText(String.valueOf(Truck.getInstance().capacity));
                    money.setText(String.valueOf(Truck.getInstance().money));
                    showTable(productTable,PRODUCT);
                    showTable(animalTable,ANIMAL);
                    showTable(orderTable,ORDER);
                    Menu.showMessage('i',"Truck was unloaded completely");
                }
            });
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Truck.getInstance().confirm();
                    capacity.setText(String.valueOf(Truck.getInstance().capacity));
                    money.setText(String.valueOf(Truck.getInstance().money));
                    //TODO
                }
            });

            jScrollPane1.setBounds(10,10,300,440);
            jScrollPane2.setBounds(320,10,300,440);
            jScrollPane3.setBounds(630,10,300,440);
            addAnimal.setBounds(10,500,130,50);
            addProduct.setBounds(165,500,130,50);
            Unload.setBounds(320,500,130,50);
            UnloadAll.setBounds(475,500,130,50);
            confirm.setBounds(630,500,130,50);
            capacity.setBounds(790,500,130,50);
            money.setBounds(790,600,130,50);

            truckMenu.setSize(W,H);

            
            container.add(jScrollPane1);
            container.add(jScrollPane2);
            container.add(jScrollPane3);
            container.add(addAnimal);
            container.add(addProduct);
            container.add(Unload);
            container.add(UnloadAll);
            container.add(confirm);
            container.add(capacity);
            container.add(money);






        }
        return truckMenu;
    }
    public static void showTable(JTable jTable,int state){
        jTable.removeAll();
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.setColumnSelectionAllowed(false);
        jTable.setModel(new DefaultTableModel(0,3));
        DefaultTableModel model=(DefaultTableModel) jTable.getModel();
        TableColumnModel columnModel=jTable.getColumnModel();
        columnModel.getColumn(0).setHeaderValue("Name");
        columnModel.getColumn(1).setHeaderValue("Quantity");
        columnModel.getColumn(2).setHeaderValue("Price");
        jTable.repaint();
        if ( (state==PRODUCT) ||(state==ORDER) ) {
            for (String type : Truck.getInstance().productIntegerHashMap.keySet()) {
                Product dummy = Product.newProduct(type);
                int a = Warehouse.getInstance().getProductIntegerHashMap().get(type) - Truck.getInstance().productIntegerHashMap.get(type);
                Object[] o = new Object[]{type, a, dummy.price};
                model.addRow(o);
            }
        }
        if ( (state==ANIMAL) ||(state==ORDER) ) {
            for (String name : Truck.getInstance().animalIntegerHashMap.keySet()) {
                Animal dummy = Domestic.newDomestic(name, false);
                if (name.equals("cat"))
                    dummy = new Cat(false);
                else if (name.equals("hound"))
                    dummy = new Hound();
                Object[] o = new Object[]{name, Truck.getInstance().animalIntegerHashMap.get(name), dummy.price};
                model.addRow(o);
            }
        }
    }
}
