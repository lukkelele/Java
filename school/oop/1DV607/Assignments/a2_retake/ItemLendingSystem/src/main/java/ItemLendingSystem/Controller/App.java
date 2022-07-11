package ItemLendingSystem.Controller;

import ItemLendingSystem.Model.*;

public class App {

    public static void main(String[] args) {
        
        Member m = new Member("Lukas", "Gunnarsson", "0707123123", "lukas@gmail.com");
        System.out.println(m);

        Calendar calendar = new Calendar();
        calendar.fastForward(5);
        System.out.println("Current day: "+calendar.getCurrentDay());


    }
}
