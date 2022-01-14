package by.stepanovich.zkh.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String email;
    private String password;
    private String userName;
    private String userSurname;
    private String phone;
    private UserStatus userStatus;
    private Role role;


    public User(String email, String password, String userName, String userSurname, String phone) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.phone = phone;
    }

    public User(int userId, String email, String password, String userName, String userSurname, String phone, UserStatus userStatus, Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userSurname = userSurname;
        this.phone = phone;
        this.userStatus = userStatus;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        User user = (User) o;
        return (userId == user.userId || (userId != null && userId.equals(user.userId))) &&
                (email == user.email || (email != null && email.equals(user.email))) &&
                (password == user.password || (password != null && password.equals(user.password))) &&
                (userName == user.userName || (userName != null && userName.equals(user.userName))) &&
                (userSurname == user.userSurname || (userSurname != null && userSurname.equals(user.userSurname))) &&
                (phone == user.phone || (phone != null && phone.equals(user.phone))) &&
                (userStatus == user.userStatus || (userStatus != null && userStatus.equals(user.userStatus))) &&
                (role == user.role || (role != null && role.equals(user.role)));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        result = prime * result + (email == null ? 0 :email.hashCode());
        result = prime * result + (password == null ? 0 :password.hashCode());
        result = prime * result + (userName == null ? 0 :userName.hashCode());
        result = prime * result + (userSurname == null ? 0 :userSurname.hashCode());
        result = prime * result + (phone == null ? 0 :phone.hashCode());
        result = prime * result + (userStatus == null ? 0 : userStatus.hashCode());
        result = prime * result + (role == null ? 0 : role.hashCode());
        return result;
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
}
