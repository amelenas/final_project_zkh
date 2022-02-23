package by.stepanovich.zkh.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable, Comparable<Order> {
    private static final long serialVersionUID = 12L;

    private long registrationId;
    private long userId;
    private String street;
    private String houseNumber;
    private String apartment;
    private String scopeOfWork;
    private Timestamp desirableTime;
    private Timestamp openingDate;
    private Timestamp closingDate;
    private OrderStatus orderStatus;
    private String pictureAddress;

    public Order() {
    }

    public Order(long registrationId, long userId, String street, String houseNumber, String apartment, String scopeOfWork,
                 Timestamp desirableTime, Timestamp openingDate, Timestamp closingDate, OrderStatus orderStatus,
                 String pictureAddress) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartment = apartment;
        this.scopeOfWork = scopeOfWork;
        this.desirableTime = desirableTime;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.orderStatus = orderStatus;
        this.pictureAddress = pictureAddress;
    }

    public Timestamp getOpeningDate() {return openingDate;}

    public Timestamp getClosingDate() {return closingDate;}

    public long getRegistrationId() {return registrationId;}

    public void setRegistrationId(long registrationId) {this.registrationId = registrationId;}

    public long getUserId() {return userId;}

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getScopeOfWork() {
        return scopeOfWork;
    }

    public void setScopeOfWork(String scopeOfWork) {
        this.scopeOfWork = scopeOfWork;
    }

    public Date getDesirableTime() {
        return desirableTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getApartment() {return apartment;}

    public void setApartment(String apartment) {this.apartment = apartment;}

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" registrationId = ").append(registrationId);
        builder.append(" userId = ").append(userId);
        builder.append(" street = ").append(street);
        builder.append(" houseNumber = ").append(houseNumber);
        builder.append(" apartment = ").append(apartment);
        builder.append(" scopeOfWork = ").append(scopeOfWork);
        builder.append(" desirableTime = ").append(desirableTime);
        builder.append(" openingDate = ").append(openingDate);
        builder.append(" closingDate = ").append(closingDate);
        builder.append(" orderStatus = ").append(orderStatus);
        builder.append(" pictureAddress = ").append(pictureAddress);
        return builder.toString();
    }

    @Override
    public int compareTo(Order o) {
        return Long.compare(registrationId, o.registrationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getRegistrationId() == order.getRegistrationId() &&
                getUserId() == order.getUserId() &&
                 Objects.equals(getStreet(), order.getStreet()) &&
                Objects.equals(getHouseNumber(), order.getHouseNumber()) && Objects.equals(getApartment(),
                order.getApartment()) && getScopeOfWork().equals(order.getScopeOfWork()) && Objects.equals(getDesirableTime(),
                order.getDesirableTime()) && Objects.equals(getOpeningDate(), order.getOpeningDate()) && Objects.equals(getClosingDate(),
                order.getClosingDate()) && getOrderStatus() == order.getOrderStatus()  && Objects.equals(getPictureAddress(), order.getPictureAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationId(), getUserId(), getStreet(), getHouseNumber(), getApartment(), getScopeOfWork(), getDesirableTime(), getOpeningDate(), getClosingDate(), getOrderStatus(), getPictureAddress());
    }
}
