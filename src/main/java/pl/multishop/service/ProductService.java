package pl.multishop.service;

import pl.multishop.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by michal on 13.06.17.
 */
public interface ProductService {

    public Product findById(int productId);

    public Product findByName(String productName);

    public void saveProduct(Product product);

    public void updateProduct(Product product);

    public void delProductById(int productId);

    public List<Product> findAllProducts();

    public Product findProductsByCategory(String productCategory);

    public boolean isProductNumberUnique(Integer productId, String productName);

}
