package entities;

import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

import java.util.Objects;

public class User implements Comparable<User> {

    private long userId;
    private MyList<Book> rated1;
    private MyList<Book> rated2;
    private MyList<Book> rated3;
    private MyList<Book> rated4;
    private MyList<Book> rated5;




    public User(long userId) {
        this.userId = userId;
        this.rated1 = new MyLinkedListImpl<>();
        this.rated2 = new MyLinkedListImpl<>();
        this.rated3 = new MyLinkedListImpl<>();
        this.rated4 = new MyLinkedListImpl<>();
        this.rated5 = new MyLinkedListImpl<>();
    }


    public long getUserId() {
        return userId;
    }


    public void addRated(Book book, int rating){
        switch (rating) {
            case 1:
                rated1.add(book);
                break;
            case 2:
                rated2.add(book);
                break;
            case 3:
                rated3.add(book);
                break;
            case 4:
                rated4.add(book);
                break;
            case 5:
                rated5.add(book);
                break;
        }
    }



    public int allRatingsSize(){

        return this.rated1.getSize() + this.rated2.getSize() + this.rated3.getSize() + this.rated4.getSize() + this.rated5.getSize() ;
    }

    public float averageRating(){
        return (this.rated1.getSize() + this.rated2.getSize()*2f + this.rated3.getSize()*3f + this.rated4.getSize()*4f + this.rated5.getSize()*5f) / allRatingsSize();
    }

    @Override
    public int hashCode() {
        return (int) userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int compareTo(User o) {
        int value = -1;

        int thisSize = this.allRatingsSize();
        int otherSize = o.allRatingsSize();
        if (thisSize == otherSize){
            value = 0;
        } else if (thisSize > otherSize){
            value = 1;
        }
        return value;
    }

    @Override
    public String toString() {
        return "User: " + userId + "\n" +
                "Cantidad: " + allRatingsSize() + "\n" +
                "Rating promedio: " + String.format("%.2f", averageRating()) + "\n" ;
    }
}
