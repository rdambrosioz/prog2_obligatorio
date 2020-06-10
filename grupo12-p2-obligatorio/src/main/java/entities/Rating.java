package entities;

import java.util.Objects;

public class Rating {

    private int rating;

    private Book book;

    public Rating(Integer rating, Book book) {
        this.rating = rating;
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(rating, rating1.rating) &&
                Objects.equals(book, rating1.book);
    }

    @Override
    public int hashCode() {
        return (((int) book.getBookId() - 1)*5) + rating;
    }



}
