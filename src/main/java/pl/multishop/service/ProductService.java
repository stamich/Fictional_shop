package pl.multishop.service;

import pl.multishop.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface of service layer for product model.
 * @author Michał Stawarski
 */
public interface ProductService {

    Product findById(int productId);

    Product findByName(String productName);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void delProductById(int productId);

    List<Product> findAllProducts();

    Product findProductsByCategory(String productCategory);

    boolean isProductNumberUnique(Integer productId, String productName);

}
