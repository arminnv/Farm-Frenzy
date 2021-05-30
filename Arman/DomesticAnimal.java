public class DomesticAnimal extends Animal{
    int productionTime;
    int life;
    final int speed=1;
    final int lifeReduction=10;
    DomesticAnimal(int price, int productionTime){
        this.price=price;
        this.productionTime=productionTime;
        life=100;
    }

}
