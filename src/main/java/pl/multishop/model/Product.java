package pl.multishop.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by michal on 05.05.17.
 */
@Entity
@Table(name = "product")
public class Product // Implementacja produktu
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private int productId;

    @Column(name = "name", nullable = false)
    private String productName;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "product_manufacturer", nullable = false)
    private String productManufacturer;

    @Column(name = "product_category", nullable = false)
    private String productCategory;

    @Column(name ="units_in_stock", nullable = false)
    private long unitsInStock;

    @Column(name ="units_in_order", nullable = false)
    private long unitsInOrder;

    @Column(name ="stan", nullable = false)
    private boolean active;

    public Product() {
        super();
    }

    public Product(int productId, String productName, BigDecimal unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
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
