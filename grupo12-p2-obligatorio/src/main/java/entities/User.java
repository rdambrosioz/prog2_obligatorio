package entities;

import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

import java.util.Objects;

public class User {

    private Long userId;
    private MyList<Book> reservedToRead;
    private MyList<Rating> ratings;




    public User(Long userId) {
        this.userId = userId;
        this.reservedToRead = new MyLinkedListImpl<>();
        this.ratings = new MyLinkedListImpl<>();
    }


    public Long getUserId() {
        return userId;
    }

    public MyList<Book> getReservedToRead() {
        return reservedToRead;
    }

    public MyList<Rating> getRatings() {
        return ratings;
    }



    @Override
    public int hashCode() {
        return userId.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

}
