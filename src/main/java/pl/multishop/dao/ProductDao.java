package pl.multishop.dao;

import java.util.List;

import pl.multishop.model.Product;

/**
 * Interface of DAO layer for product model.
 * @author Michał Stawarski
 */
public interface ProductDao {

    Product findById(int productId);
    Product findByName(String productName);
    void saveProduct(Product product);
    void delProductById(int productId);
    List<Product> findAllProducts();
    Product findProductsByCategory(String productCategory);

}
