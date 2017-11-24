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
    List <Product> getAllProducts();

    Product getProductById(String productID);

    List<Product> getProductsByCategory(String category);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
}
