public class Product {
    int space;
    int price;
    protected static final int PRIMARY_PRODUCT_SPACE=1;
    protected static final int SECONDARY_PRODUCT_SPACE=1;
    protected static final int FINAL_PRODUCT_SPACE=1;
    Product(int price,int space){
        this.price=price;
        this.space=space;
    }
}
