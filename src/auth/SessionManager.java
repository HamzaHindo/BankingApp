package auth;

import java.util.UUID;

public class SessionManager {
    private UUID currentUserId;

    public UUID getCurrentUserId() {
        return currentUserId;
    }

    void setCurrentUser(UUID id) {
        this.currentUserId = id;
    }

    void clearSession() {
        this.currentUserId = UUID.fromString("");
    }
}
