package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.model.Product;
import pl.multishop.dao.ProductDao;

import java.util.List;

@Service("productService")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(int productId) {
        return productDao.findById(productId);
    }

    @Override
    public Product findByName(String productName) {
        return productDao.findByName(productName);
    }

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        Product entity = productDao.findById(product.getProductId());
        if(entity!=null){
            //entity.setProductId(product.getProductId());//updatable = false
            entity.setProductName(product.getProductName());
            entity.setUnitPrice(product.getUnitPrice());
            entity.setProductDescription(product.getProductDescription());
            entity.setProductManufacturer(product.getProductManufacturer());
            entity.setProductCategory(product.getProductCategory());
            entity.setUnitsInStock(product.getUnitsInStock());
            entity.setUnitsInOrder(product.getUnitsInOrder());
            entity.setActive(product.isActive());
        }
    }

    @Override
    public void delProductById(int productId) {
        productDao.delProductById(productId);
    }

    @Override
    public List<Product> findAllProducts() {
        return productDao.findAllProducts();
    }

    @Override
    public Product findProductsByCategory(String productCategory) {
        return productDao.findProductsByCategory(productCategory);
    }

    @Override
    public boolean isProductNumberUnique(Integer productId, String productName){
        Product product = findByName(productName);
        return (product == null || (productId != null) && product.getProductId() == productId);
    }
}
