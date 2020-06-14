package entities;

import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

import java.util.Objects;

public class AuthorPublications implements Comparable<AuthorPublications> {

    private Author author;
    private int year;
    private MyList<Book> books;


    public AuthorPublications(Author author, int year) {
        this.author = author;
        this.year = year;
        this.books = new MyLinkedListImpl<>();
    }

    public Author getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
    public void addBook(Book book){
        this.books.add(book);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorPublications that = (AuthorPublications) o;
        return year == that.year &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {

        return Math.abs(Objects.hash(author, year));
    }


    @Override
    public int compareTo(AuthorPublications o) {
        int value = -1;

        float thisAvg = this.books.getSize();
        float otherAvg = o.books.getSize();
        if (thisAvg == otherAvg){
            value = 0;
        } else if (thisAvg > otherAvg){
            value = 1;
        }
        return value;
    }

    @Override
    public String toString() {
        return  "Autor: " + author + "\n" +
                "Año de publicación: " + year + "\n" +
                "Cantidad: " + books.getSize() + "\n" + "\n"
                ;
    }
}
