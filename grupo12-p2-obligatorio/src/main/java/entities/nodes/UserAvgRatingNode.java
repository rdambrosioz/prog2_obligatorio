package entities.nodes;

import entities.User;

public class UserAvgRatingNode implements Comparable<UserAvgRatingNode>{

    private User user;

    public UserAvgRatingNode(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int compareTo(UserAvgRatingNode o) {
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
}
