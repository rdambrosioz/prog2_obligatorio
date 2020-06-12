package entities;

import java.util.Objects;

public class BookBookingsHeapNode implements Comparable<BookBookingsHeapNode>{

    private Book book;

    public BookBookingsHeapNode(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookBookingsHeapNode that = (BookBookingsHeapNode) o;
        return Objects.equals(book, that.book);
    }


    @Override
    public int compareTo(BookBookingsHeapNode o) {
        int value = -1;

        int thisSize = this.book.bookedBySize();
        int otherSize = o.book.bookedBySize();
        if (thisSize == otherSize){
            value = 0;
        } else if (thisSize > otherSize){
            value = 1;
        }
        return value;
    }
}
