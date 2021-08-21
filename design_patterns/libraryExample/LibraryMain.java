package libraryExample;

public class LibraryMain {

    public static void main(String[] args) {
        
        Patron patron1 = new Patron();
        Book book1 = new Book();
        Book book2 = new Book("Harry Potter and the Sorcerers Stone", "J.K. Rowling");
    

        patron1.setName("Lukas");
        book1.setTitle("kegan");

        patron1.makeLoan(book1);
        patron1.makeLoan(book2);
        patron1.makeLoan(book1);

        patron1.showBooks();

        patron1.returnBook(book1);

        

    }
    
}
