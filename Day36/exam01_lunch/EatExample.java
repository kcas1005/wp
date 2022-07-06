package Day36.exam01_lunch;

public class EatExample {
    public static void main(String[] args) {
        Eat myeat = new Eat();
        People pkim = new Kim();
        People ppark = new Park();
        People plee = new Lee();
        Jelly jmango = new Mango();
        Jelly jstrawberry = new Strawberry();
        Jelly jgrape = new Grape();

        myeat.Eating(myeat.Select_People(plee), myeat.Select_Jelly(jmango));


    }
}
