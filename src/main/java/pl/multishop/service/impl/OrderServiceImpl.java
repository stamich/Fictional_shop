package pl.multishop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.multishop.domain.Product;
import pl.multishop.domain.repository.ProductRepository;
import pl.multishop.service.OrderService;

/**
 * Created by michal on 06.05.17.
 */

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductRepository productRepository;

    public void processOrder(String productId, long quantity) {
        Product productById = productRepository.getProductById(productId);

        if(productById.getUnitsInStock() < quantity){
            throw new IllegalArgumentException("Zbyt mało towaru. Obecna liczba sztuk w magazynie "+ productById.getUnitsInStock());
        }

        productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
    }
}
