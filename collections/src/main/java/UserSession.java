import java.util.Objects;

public class UserSession {
    private final String sessionId;
    private final String userId;

    public UserSession(String sessionId, String userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    // Getters and other methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession that = (UserSession) o;
        return Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, userId);
    }
}
