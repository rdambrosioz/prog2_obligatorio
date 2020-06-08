package entities;

import uy.edu.um.prog2.adt.list.MyList;

import java.util.Objects;

public class User {
    public Long getUserId() {
        return userId;
    }

    public MyList<Book> getReservedToRead() {
        return reservedToRead;
    }

    public MyList<Rating> getRatings() {
        return ratings;
    }

    private Long userId;

    private MyList<Book> reservedToRead;
    private MyList<Rating> ratings;

    public User(Long userId) {
        this.userId = userId;
    }


}
