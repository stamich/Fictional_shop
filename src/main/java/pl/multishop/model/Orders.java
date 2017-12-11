package pl.multishop.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The POJO (Plain Old Java Object) class for order model.
 * @author Michał Stawarski
 * @version 1.0
 */
@Entity
@Table(name = "zamowienie")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numer_zamowienia", nullable = false, unique = true, updatable = false)
    private int orderId;

    @Column(name = "numer_klienta", nullable = false, unique = true, updatable = false)
    private String clientId;

    @Column(name = "numer_produktu", nullable = false, unique = true, updatable = false)
    private int productId;

    @Column(name = "kwota", nullable = false)
    private double amount;

    @Column(name = "data_zamowienia", nullable = false)
    private LocalDate orderDate;

    @Column(name = "status_zamowienia", nullable = false)
    private String orderStatus;

    public Orders(){
        super();
    }

    public Orders(int orderId, String clientId, double amount){

        this.orderId = orderId;
        this.clientId = clientId;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        return orderId == orders.orderId;
    }

    @Override
    public int hashCode() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
