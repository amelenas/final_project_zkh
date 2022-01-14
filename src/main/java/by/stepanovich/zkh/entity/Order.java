package by.stepanovich.zkh.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer registrationId;//1
    private Integer userId;//2
    private String street;//3
    private String houseNumber;//4
    private String scopeOfWork;//5
    private Date desirableTime;//6
    private Integer orderStatus;//7
    private String additionalInformation;//8
    private String pictureAddress;//9
    private boolean isPrivate;//10
    private Integer mark;//11

    public Order() {
    }

    public Order(Integer registrationId, Integer userId, String street,
                 String houseNumber, String scopeOfWork, Date desirableTime,
                 Integer orderStatus, String additionalInformation, String pictureAddress,
                 boolean isPrivate, Integer mark) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.scopeOfWork = scopeOfWork;
        this.desirableTime = desirableTime;
        this.orderStatus = orderStatus;
        this.additionalInformation = additionalInformation;
        this.pictureAddress = pictureAddress;
        this.isPrivate = isPrivate;
        this.mark = mark;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public void setDesirableTime(Date desirableTime) {
        this.desirableTime = desirableTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

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

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return (registrationId == order.registrationId || (registrationId != null && registrationId.equals(order.registrationId))) &&
                (userId == order.userId || (userId != null && userId.equals(order.userId))) &&
                (street == order.street || (street != null && street.equals(order.street))) &&
                (houseNumber == order.houseNumber || (houseNumber != null && houseNumber.equals(order.houseNumber))) &&
                (scopeOfWork == order.scopeOfWork || (scopeOfWork != null && scopeOfWork.equals(order.scopeOfWork))) &&
                (desirableTime == order.desirableTime || (desirableTime != null && desirableTime.equals(order.desirableTime))) &&
                (orderStatus == order.orderStatus || (orderStatus != null && orderStatus.equals(order.orderStatus))) &&
                (additionalInformation == order.additionalInformation || (additionalInformation != null && additionalInformation.equals(order.additionalInformation))) &&
                (pictureAddress == order.pictureAddress || (pictureAddress != null && pictureAddress.equals(order.pictureAddress))) &&
                (isPrivate == order.isPrivate ) && (mark == order.mark || (mark != null && mark.equals(order.mark)));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (registrationId == null ? 0 : registrationId.hashCode());
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        result = prime * result + (street == null ? 0 : street.hashCode());
        result = prime * result + (houseNumber == null ? 0 : houseNumber.hashCode());
        result = prime * result + (scopeOfWork == null ? 0 : scopeOfWork.hashCode());
        result = prime * result + (desirableTime == null ? 0 : desirableTime.hashCode());
        result = prime * result + (orderStatus == null ? 0 : orderStatus.hashCode());
        result = prime * result + (additionalInformation == null ? 0 : additionalInformation.hashCode());
        result = prime * result + (pictureAddress == null ? 0 : pictureAddress.hashCode());
        result = prime * result + (Boolean.hashCode(isPrivate));
        result = prime * result + (mark == null ? 0 : mark.hashCode());
        return result;
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
}
