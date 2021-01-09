import java.util.Objects;

/**
 * Data Model for holding score info
 */
public class Score implements Comparable<Score> {
    private User user; // user object
    private int score; // score value

    public Score(User user, int score) {
        this.user = user;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Score o) {
        return o.getScore() - this.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && Objects.equals(user, score1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, score);
    }
}
