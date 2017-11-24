package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.multishop.dao.ProductDao;
import pl.multishop.model.Product;

/**
 * Created by michal on 06.05.17.
 */

@Service
public class OrderServiceImpl implements OrderService // Implementacja zamówienia
{
    @Autowired
    private ProductDao productRepository;

    public void processOrder(String productId, long quantity)
    {
        Product productById = productRepository.getProductById(productId);

        if(productById.getUnitsInStock() < quantity)
        {
            throw new IllegalArgumentException("Zbyt mało towaru. Obecna liczba sztuk w magazynie " + productById.getUnitsInStock());
        }
        productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
    }
}
