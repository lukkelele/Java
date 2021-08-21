public class app {

    public static void main(String[] args) {
        
        System.out.println("Software started.");

        Animal cheetah = new Animal();
        Animal cat = new Animal("Malte", 15, "fenile");
        
        cheetah.setName("Cheeto");

        System.out.println("The name of cheetah: "+cheetah.getName());
        System.out.println("The name of cat: "+cat.getName());

        Cat newCat = new Cat();
        newCat.setName("Caty Purry Billy");
        newCat.setWeight(30);
        Cat secondCat = new Cat("Caty Purry", 25, "Feline");

        System.out.println(cheetah.getName());
        cheetah.setName("TEST");
        System.out.println(cheetah.getName());
        System.out.println("New cat name: "+newCat.getName());
        System.out.println("New cat weight: "+newCat.getWeight());
        System.out.println("Second cat name: "+secondCat.getName());
        System.out.println("Second cat weight: "+secondCat.getWeight());
        
        
        System.out.println(cat.getName()+"  &  "+cheetah.getName());
        newCat.setName("NEW DRIP");
        System.out.println( newCat.getName() );
    }

}
