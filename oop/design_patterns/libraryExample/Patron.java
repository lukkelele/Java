package libraryExample;

import java.util.ArrayList;


public class Patron {

    private String name;
    private int patreonNumber;
    private int loans;
    private ArrayList<Book> loanedBooks = new ArrayList<>();

    Patron(){
        loans=0;
    }

    public void makeLoan(Book book) {
        if (book.showPatron() == null && book.showPatron() != this) {
            loanedBooks.add(book);
            loans++;
            book.addPatron(this);
        }
        else {
            System.out.println("Book is already loaned");
        }
    }

    public void returnBook(Book book) {
        book.returnLoan(book);
        loanedBooks.remove(book);
    }

    public void showBooks(){
        for (Book b : loanedBooks) {
            System.out.print(b.getTitle()+", "+b.getAuthor()+"\n");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
