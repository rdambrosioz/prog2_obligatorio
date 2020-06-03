package entities;

import uy.edu.um.prog2.adt.list.MyList;

public class User {

    private Long userId;

    private MyList<Book> reservedToRead;
    private MyList<Rating> ratings;

    public User(Long userId) {
        this.userId = userId;
    }
}
