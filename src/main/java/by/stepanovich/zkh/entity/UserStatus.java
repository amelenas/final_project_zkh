package by.stepanovich.zkh.entity;

public enum UserStatus {
    VALID(1),
    BLOCKED(2);

    private final int status;

    UserStatus(int status) {
        this.status = status;
    }

    public static UserStatus of(String status) {
        for (UserStatus value : UserStatus.values()) {
            if (value.status == Integer.parseInt(status)) {
                return value;
            }
        }
        return BLOCKED;
    }

    public UserStatus reverse() {
        return this == VALID ? BLOCKED : VALID;
    }

    public int getInt() {
        return status;
    }
}
