public class Animal implements AnimalInterface{

    private String name;
    private int weight;
    private String type;
    
    Animal(String name, int weight, String type){
        this.name = name;
        this.weight = weight;
        this.type = type;
    }

    Animal(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public void roar() {
        System.out.println("roar");
    }

    @Override
    public void eat() {
        System.out.println("eating nomnom");
    }
}
