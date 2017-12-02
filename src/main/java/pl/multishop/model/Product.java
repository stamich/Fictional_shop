package pl.multishop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by michal on 05.05.17.
 */
@Entity
@Table(name = "produkt")
public class Product // Implementacja produktu
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numer_produktu", nullable = false, unique = true, updatable = false)
    private int productId;

    @Column(name = "nazwa", nullable = false)
    private String productName;

    @Column(name = "cena_jedn", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "opis_produktu", nullable = false)
    private String productDescription;

    @Column(name = "producent", nullable = false)
    private String productManufacturer;

    @Column(name = "kategoria", nullable = false)
    private String productCategory;

    @Column(name ="na_stanie", nullable = false)
    private long unitsInStock;

    @Column(name ="zamowione", nullable = false)
    private long unitsInOrder;

    @Column(name ="aktywny", nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "zamowienie", joinColumns = { @JoinColumn(name = "numer_produktu") }, inverseJoinColumns = { @JoinColumn(name = "numer_klienta") })
    private Set<Client> clients = new HashSet<Client>(0);

    public Product() {
        super();
    }

    public Product(int productId, String productName, Set<Client> clients) {
        this.productId = productId;
        this.productName = productName;
        this.clients = clients;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return productId;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + productName + "]";
    }
}
