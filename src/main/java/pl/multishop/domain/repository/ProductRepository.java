package pl.multishop.domain.repository;

import java.util.List;

import pl.multishop.domain.Product;

/**
 * Created by michal on 05.05.17.
 */

public interface ProductRepository
{
    List <Product> getAllProducts();

    Product getProductById(String productID);
}
