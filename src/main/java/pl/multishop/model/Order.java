package pl.multishop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The POJO (Plain Old Java Object) class for order model.
 * @author Michał Stawarski
 * @version 1.0
 */
@Entity
@Table(name = "ORDER")
public class Order extends AbstractDomainObject<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false, unique = true, updatable = false)
    private Long id;

//    @Column(name = "numer_klienta", nullable = false, unique = true, updatable = false)
//    private String clientId;
//
//    @Column(name = "numer_produktu", nullable = false, unique = true, updatable = false)
//    private int productId;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

//    @Column(name = "data_zamowienia", nullable = false)
//    private LocalDate orderDate;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID", nullable = false)
    private Client client;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    //

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    //

    @Override
    public String print() {
        return null;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        super.setCreatedAt(createdAt);
    }

    @Override
    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    @Override
    public void setCreatedBy(String createdBy) {
        super.setCreatedBy(createdBy);
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return super.getUpdatedAt();
    }

    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {
        super.setUpdatedAt(updatedAt);
    }

    @Override
    public String getUpdatedBy() {
        return super.getUpdatedBy();
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        super.setUpdatedBy(updatedBy);
    }
}
