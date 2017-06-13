package pl.multishop.service;

import pl.multishop.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by michal on 13.06.17.
 */
public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(String productID);

    List<Product> getProductsByCategory(String category);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
}
