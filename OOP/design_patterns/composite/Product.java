package CodeForLectures.lecture5.composite;

public class Product implements Component {
    private String name;
    private int price;

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public int calculatePrice() {
        return price;
    }
    
}
