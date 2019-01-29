package pl.multishop.model;

public enum Status {

    NEW("NEW"),
    DELAYED("DELAYED"),
    CONFIRMED("CONFIMED"),
    SHIPPED("SHIPPED"),
    CANCELLED("CANCELLED");

    private String status;

    private Status (String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
