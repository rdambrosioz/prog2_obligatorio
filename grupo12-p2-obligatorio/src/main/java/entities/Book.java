package entities;

import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

import javax.print.DocFlavor;

public class Book {

    private long bookId;
    private String isbn;
    private Integer originalPublicationYear;
    private String originalTitle;
    private String title;
    private String imageUrl;
    private MyList<User> bookedBy;
    private MyList<Author> authors;
    private MyList<User> ratedBy;

    public Book(long bookId, String isbn, Integer originalPublicationYear, String originalTitle, String title, String imageUrl) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.authors = new MyLinkedListImpl<>();
        this.originalPublicationYear = originalPublicationYear;
        this.originalTitle = originalTitle;
        this.title = title;
        this.imageUrl = imageUrl;
        this.bookedBy = new MyLinkedListImpl<>();
        this.ratedBy = new MyLinkedListImpl<>();

    }

    public MyList<User> getRatedBy() {
        return ratedBy;
    }

    public String getTitle() {
        return title;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public int ratedBySize(){
        return this.ratedBy.getSize();
    }

    public void addBooking(User user){
        this.bookedBy.add(user);
    }

    public int bookedBySize(){
        return this.bookedBy.getSize();
    }

    public long getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return  "Id del libro: " + bookId + "\n" +
                "Titulo: " + title + "\n" +
                "Cantidad: " + this.bookedBy.getSize() + "\n"
                ;
    }


}
