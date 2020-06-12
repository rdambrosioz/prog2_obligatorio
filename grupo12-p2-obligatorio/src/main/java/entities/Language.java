package entities;

import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

import java.util.Objects;

public class Language implements Comparable<Language> {

    private String languageCode;
    private MyList<Book> booksInThisLanguage;

    public Language(String languageCode) {

        this.languageCode = languageCode;
        this.booksInThisLanguage = new MyLinkedListImpl<>();

    }

    public MyList<Book> getBooksInThisLanguage() {
        return booksInThisLanguage;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public int booksAmount(){
        return this.booksInThisLanguage.getSize();
    }

    public void addBook(Book book){
        this.booksInThisLanguage.add(book);
    }

    public int countBookings(){

        int bookings = 0;

        for (Book book : booksInThisLanguage){

            bookings += book.bookedBySize();

        }
        return bookings;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(languageCode, language.languageCode);
    }


    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(languageCode));
    }

    @Override
    public int compareTo(Language o) {

        int toReturn = 0;

        if (this.countBookings() > o.countBookings()){
            toReturn = 1;
            return toReturn;
        }

        if (this.countBookings() < o.countBookings()){
            toReturn = -1;
            return toReturn;
        }
        return toReturn;
    }

    @Override
    public String toString() {
        return "Language: " +
                 languageCode + "\n" + "Cantidad: " + countBookings() + "\n";
    }
}
