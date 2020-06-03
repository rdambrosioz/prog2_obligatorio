package entities;

import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

import javax.print.DocFlavor;

public class Book {

    private Long bookId;
    private String isbn;
    private Integer originalPublicationYear;
    private String originalTitle;
    private String title;
    private String languageCode;
    private String imageUrl;

    private MyList<Author> authors;

    public Book(Long bookId, String isbn, Integer originalPublicationYear, String originalTitle, String title, String language_code, String imageUrl) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.authors = new MyLinkedListImpl<>();
        this.originalPublicationYear = originalPublicationYear;
        this.originalTitle = originalTitle;
        this.title = title;
        this.languageCode = language_code;
        this.imageUrl = imageUrl;
    }




    @Override
    public String toString() {
        return  "book_id: " + bookId + "\n" +
                "isbn: " + isbn + "\n" +
                "authors: " + authors + "\n" +
                "original_publication_year: " + originalPublicationYear + "\n" +
                "original_title: " +originalTitle+ "\n" +
                "title: " + title + "\n" +
                "language_code: " + languageCode + "\n" +
                "image_url: " + imageUrl + "\n" +
                "\n\n";
    }

}
