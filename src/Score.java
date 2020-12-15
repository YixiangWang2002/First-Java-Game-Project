public class Score implements Comparable<Score> {
    private User user;
    private int score;

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
}
