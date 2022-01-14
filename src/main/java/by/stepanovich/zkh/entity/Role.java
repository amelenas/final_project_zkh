package by.stepanovich.zkh.entity;

public enum Role {
    USER(1),
    ADMIN(2),
    EMPLOYEE(3),
    UNREGISTERED(4);

    private final int role;

    Role(int role) {
        this.role = role;
    }

    public static Role of(String role) {
        for (Role value : Role.values()) {
            if (value.role == Integer.parseInt(role)) {
                return value;
            }
        }
        return EMPLOYEE;
    }
}
