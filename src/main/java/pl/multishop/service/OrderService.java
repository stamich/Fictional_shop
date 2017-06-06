package pl.multishop.service;

/**
 * Created by michal on 06.06.17.
 */
public interface OrderService {

    void processOrder(String  productId, long quantity);
}