package ItemLendingSystem.Controller;

import ItemLendingSystem.Model.*;



public class DataLoader {
   /* Class for hardcoded data ONLY */ 
   
    public DataLoader() {

    }
    
    public static void loadHardcodedData(Admin admin) {
        Member member1 = new Member("Lukkelele", "Mahmoud", "0207123123", "lukkelelMahmoud@gmail.com");
        Member member2 = new Member("Yngve", "Kegansson", "01237123123", "yngveYehaw@gmai222l.com");
        Member member3 = new Member("Lukas", "Gunnarsson", "0707123123", "lukas@kingen.com");
        Item item1 = new Item("Hammer", "", 2, 15, Item.Category.TOOL);
        Item item2 = new Item("Drill", "", 1, 35, Item.Category.TOOL);
        Item item3 = new Item("RC car", "", 4, 23, Item.Category.TOY);
        Item item4 = new Item("RC car", "", 2, 30, Item.Category.TOY);
        Item item5 = new Item("Buzz Lightyear", "", 5, 2, Item.Category.TOY);
        Item item6 = new Item("Sledgehammer", "", 3, 12, Item.Category.TOOL);
        Item item7 = new Item("Watercutter", "", 7, 19, Item.Category.TOOL);
        Item item8 = new Item("iPad", "", 1, 9, Item.Category.OTHER);
        Item item9 = new Item("Football", "", 1, 2, Item.Category.SPORT);

        admin.registerMember(member1);
        admin.registerMember(member2);
        admin.registerMember(member3);

        member1.addOwnedItem(item1);
        member1.addOwnedItem(item2);

        member2.addOwnedItem(item3);
        member2.addOwnedItem(item8);
        member2.addOwnedItem(item9);

        member3.addOwnedItem(item4);
        member3.addOwnedItem(item5);
        member3.addOwnedItem(item6);
        member3.addOwnedItem(item7);

        admin.loanItem(member1, member3, item1, 4);
        admin.loanItem(member3, member1, item7, 10);
        admin.loanItem(member2, member1, item9, 3);
        admin.loanItem(member1, member2, item2, 6);

    }


}
