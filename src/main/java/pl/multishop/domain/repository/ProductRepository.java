package pl.multishop.domain.repository;

import java.util.List;

import pl.multishop.domain.Product;

public interface ProductRepository {

    List <Product> getAllProducts();

    Product getProductById(String productID);
}
