package pl.multishop.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The POJO (Plain Old Java Object) class for order model.
 * @author Michał Stawarski
 * @version 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "ORDER")
@SuppressWarnings("serial")
public class Order extends AbstractDomainObject<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID", nullable = false)
    private Client client;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    @Override
    public String print() {
        return "Order: " + getId();
    }
}
