package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.multishop.model.Product;
import pl.multishop.dao.ProductDao;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by michal on 13.05.17.
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(String productID) {
        return productRepository.getProductById(productID);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        return productRepository.getProductsByFilter(filterParams);
    }

}
