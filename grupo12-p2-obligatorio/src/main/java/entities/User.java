package entities;

import uy.edu.um.prog2.adt.list.MyList;
import uy.edu.um.prog2.adt.list.linkedlist.MyLinkedListImpl;

import java.util.Objects;

public class User implements Comparable<User> {

    private long userId;
    private MyList<Rating> ratings;




    public User(long userId) {
        this.userId = userId;
        this.ratings = new MyLinkedListImpl<>();
    }


    public long getUserId() {
        return userId;
    }


    public MyList<Rating> getRatings() {
        return ratings;
    }



    public int allRatingsSize(){
        return this.ratings.getSize();
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
                "Rating promedio: " + "\n" ;
    }
}
