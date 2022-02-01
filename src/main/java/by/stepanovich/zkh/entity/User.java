package by.stepanovich.zkh.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable, Comparable<User> {
    private static final long serialVersionUID = 1L;
    private long userId;
    private String email;
    private String password;
    private String userName;
    private String userSurname;
    private String phone;
    private UserStatus userStatus;
    private Role role;

    public User() {
    }

    public User(String email, String password, String userName, String userSurname, String phone) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.phone = phone;
    }

    public User(long userId, String email, String password, String userName, String userSurname, String phone, UserStatus userStatus, Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.phone = phone;
        this.userStatus = userStatus;
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public UserStatus setUserStatus(UserStatus userStatus) {
        return this.userStatus = userStatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(int idRole) {
        this.role = role;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" userId = ").append(userId);
        builder.append(", email = ").append(email);
        builder.append(", password = ").append(password);
        builder.append(", userName = ").append(userName);
        builder.append(", surName = ").append(userSurname);
        builder.append(", phone = ").append(phone);
        builder.append(", userStatus = ").append(userStatus);
        builder.append(", idRole = ").append(role);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() && getEmail().equals(user.getEmail())
                && getPassword().equals(user.getPassword()) && getUserName().equals(user.getUserName())
                && getUserSurname().equals(user.getUserSurname()) && getPhone().equals(user.getPhone()) &&
                getUserStatus() == user.getUserStatus() && getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getEmail(), getPassword(), getUserName(),
                getUserSurname(), getPhone(), getUserStatus(), getRole());
    }


    @Override
    public int compareTo(User o) {
        return Long.compare(userId, o.userId);
    }
}
