package by.stepanovich.zkh.entity;

public enum OrderStatus {
    IN_PROCESSING(1),
    COMPLETED(2),
    AT_WORK(3),
    CANCELLED(4);

    private final int orderStatus;

    OrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static OrderStatus of(String orderStatus) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.orderStatus == Integer.parseInt(orderStatus)) {
                return value;
            }
        }
        return AT_WORK;
    }
}
