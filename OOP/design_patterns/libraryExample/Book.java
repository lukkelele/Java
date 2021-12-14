package libraryExample;

public class Book {

    private String title;
    private String author;
    private Patron patron;

    Book(){
    }

    Book(String name, String Author){
        this.title = name;
        this.author = Author;
    }

    public void setTitle(String title) {
        if (title == "kegan"){
            System.out.println("Atilla miniKädd");
        }
        else {    
        this.title = title;
        }
    }

    public Patron showPatron() {
        return this.patron;
    }

    public void addPatron(Patron patron){
        this.patron = patron;
    }
    
    public void returnLoan(Book book) {
        this.patron = null;
    }

    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }


}
