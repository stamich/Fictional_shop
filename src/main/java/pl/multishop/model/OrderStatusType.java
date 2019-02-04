package pl.multishop.model;

public enum OrderStatusType {

    NEW("NEW"),
    DELAYED("DELAYED"),
    CONFIRMED("CONFIMED"),
    SHIPPED("SHIPPED"),
    CANCELLED("CANCELLED");

    private String status;

    private OrderStatusType(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
