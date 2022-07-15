package CodeForLectures.lecture5.composite;

public class CompositeMain {
    public static void main(String[] args) {
        Box smartphoneBox = new Box();
        smartphoneBox.add(new Product("myPhone X", 599));
        
        Box earphones = new Box();
        earphones.add(new Product("phonies", 99));
        earphones.add(new Product("guarantee", 2));

        smartphoneBox.add(earphones);

        Box charger = new Box();
        charger.add(new Product("simple charger", 79));
        charger.add(new Product("cord", 19));

        smartphoneBox.add(charger);

        System.out.println("Total price: " + smartphoneBox.calculatePrice());

    }
}
