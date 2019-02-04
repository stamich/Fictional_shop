package pl.multishop.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * The POJO (Plain Old Java Object) class for product model.
 * @author Michał Stawarski
 * @version 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "PRODUCT")
@SuppressWarnings("serial")
public class Product extends AbstractDomainObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String productName;

    @Column(name = "PRICE", nullable = false)
    private Double unitPrice;

    @Column(name = "DESCRIPTION", nullable = false)
    private String productDescription;

    @Column(name = "MANUFACTURER", nullable = false)
    private String productManufacturer;

    @Column(name = "CATEGORY", nullable = false)
    private String productCategory;

    @Column(name ="IN_STOCK", nullable = false)
    private long unitsInStock;

    @Column(name ="IN_ORDER", nullable = false)
    private long unitsInOrder;

    @Column(name ="ACTIVE", nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "order", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = { @JoinColumn(name = "client_id") })
    private Set<Client> clients = new HashSet<Client>(0);

    public Product() {
        super();
    }

    public Product(Long id, String productName, Set<Client> clients) {
        this.id = id;
        this.productName = productName;
        this.clients = clients;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public long getUnitsInOrder() {
        return unitsInOrder;
    }

    public void setUnitsInOrder(long unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String print() {
        return "Product: " + getId() + " " + getProductName();
    }
}
