package entities;

public class Rating {

    private Integer rating;

    private Book book;

    public Rating(Integer rating, Book book) {
        this.rating = rating;
        this.book = book;
    }
}
