package entities.heapNodes;

import entities.Book;

public class BookRatedHeapNode implements Comparable<BookRatedHeapNode> {

    private Book book;

    public BookRatedHeapNode(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
    @Override
    public String toString() {
        return "Id del libro: " + book.getBookId() + "\n" +
                "Titulo: " + book.getTitle() + "\n" +
                "Cantidad: " + book.ratedBySize() + "\n"
                ;
    }
    @Override
    public int compareTo(BookRatedHeapNode o) {

        int toReturn = 0;

        if (this.book.ratedBySize() > o.book.ratedBySize()){
            toReturn = 1;
        }
        if (this.book.ratedBySize() < o.book.ratedBySize()){
            toReturn = -1;
        }

        return toReturn;
    }
}
