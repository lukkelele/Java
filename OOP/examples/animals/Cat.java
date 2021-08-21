public class Cat extends Animal {


    public Cat(){

    }

    public Cat(String name, int weight, String type){
        super(name, weight, type);
    }

    @Override
    public void roar(){
        System.out.println("Meoooooow");
    }

    @Override
    public void eat(){
        System.out.println("*eats*");
    }
    
    @Override
    public void setName(String name) {
    
        super.setName(name);        // SUPER !!!
    }
}
