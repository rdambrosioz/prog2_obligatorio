package entities.heapNodes;

import entities.User;

public class UserAvgRatingHeapNode implements Comparable<UserAvgRatingHeapNode>{

    private User user;

    public UserAvgRatingHeapNode(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int compareTo(UserAvgRatingHeapNode o) {
        int value = -1;

        float thisAvg = this.user.averageRating();
        float otherAvg = o.user.averageRating();
        if (thisAvg == otherAvg){
            value = 0;
        } else if (thisAvg < otherAvg){
            value = 1;
        }
        return value;
    }

    @Override
    public String toString() {
        return "User: " + user.getUserId() + "\n" +
                "Cantidad: " + user.allRatingsSize() + "\n" +
                "Rating promedio: " + String.format("%.2f", user.averageRating()) + "\n" ;
    }
}
