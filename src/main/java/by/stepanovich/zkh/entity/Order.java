package by.stepanovich.zkh.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
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
    private String additionalInformation;
    private String pictureAddress;
    private boolean isPrivate;
    private int mark;

    public Order() {
    }

    public Order(long registrationId, long userId, String street, String houseNumber, String apartment, String scopeOfWork,
                 Timestamp desirableTime, Timestamp openingDate, Timestamp closingDate, OrderStatus orderStatus, String additionalInformation,
                 String pictureAddress, boolean isPrivate, int mark) {
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
        this.additionalInformation = additionalInformation;
        this.pictureAddress = pictureAddress;
        this.isPrivate = isPrivate;
        this.mark = mark;
    }

    public Timestamp getOpeningDate() {return openingDate;}

    public void setOpeningDate(Timestamp openingDate) {this.openingDate = openingDate;}

    public Timestamp getClosingDate() {return closingDate;}

    public void setClosingDate(Timestamp closingDate) {this.closingDate = closingDate;}

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

    public void setDesirableTime(Timestamp desirableTime) {
        this.desirableTime = desirableTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getApartment() {return apartment;}

    public void setApartment(String apartment) {this.apartment = apartment;}
    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass());
        builder.append(" registrationId = ").append(registrationId);
        builder.append(" userId = ").append(userId);
        builder.append(" street = ").append(street);
        builder.append(" houseNumber = ").append(houseNumber);
        builder.append(" scopeOfWork = ").append(scopeOfWork);
        builder.append(" desirableTime = ").append(desirableTime);
        builder.append(" orderStatus = ").append(orderStatus);
        builder.append(" additionalInformation = ").append(additionalInformation);
        builder.append(" pictureAddress = ").append(pictureAddress);
        builder.append(" isPrivate = ").append(isPrivate);
        builder.append(" mark = ").append(mark);
        return builder.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getRegistrationId() == order.getRegistrationId() && getUserId() == order.getUserId()
                && getOrderStatus() == order.getOrderStatus() && isPrivate() == order.isPrivate() &&
                getMark() == order.getMark() && getStreet().equals(order.getStreet()) &&
                getHouseNumber().equals(order.getHouseNumber()) && getScopeOfWork().equals(order.getScopeOfWork())
                && getDesirableTime().equals(order.getDesirableTime()) && openingDate.equals(order.openingDate) &&
                closingDate.equals(order.closingDate) && getAdditionalInformation().equals(order.getAdditionalInformation()) &&
                getPictureAddress().equals(order.getPictureAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationId(), getUserId(), getStreet(), getHouseNumber(), getScopeOfWork(),
                getDesirableTime(), openingDate, closingDate, getOrderStatus(), getAdditionalInformation(), getPictureAddress(), isPrivate(), getMark());
    }
}
