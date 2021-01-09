import java.util.Objects;

/**
 * Data model for holding user info.
 */
public class User {
    private String userId; // user id
    private UserType userType; // user type enum

    public User(String userId, UserType userType) {
        this.userId = userId;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equalsIgnoreCase(user.userId) && userType.equals(user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userType);
    }
}
