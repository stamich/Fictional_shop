package pl.multishop.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.multishop.model.Product;

/**
 * Created by michal on 05.05.17.
 */

public interface ProductDao // Interfejs warstwy danych
{
    public Product findById(int productId);

    public Product findByName(String productName);

    public void saveProduct(Product product);

    public void delProductById(int productId);

    public List<Product> findAllProducts();

    public Product findProductsByCategory(String productCategory);

}
