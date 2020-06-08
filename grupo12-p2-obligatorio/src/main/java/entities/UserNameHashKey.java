package entities;

import java.util.Objects;

public class UserNameHashKey {
    private Long userId;

    public UserNameHashKey(Long userId) {
        this.userId = userId;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNameHashKey that = (UserNameHashKey) o;
        return Objects.equals(userId, that.userId);
    }

    
    public int hashCode() {
        return userId.intValue();
    }
}
