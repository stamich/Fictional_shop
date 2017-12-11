package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.model.Product;
import pl.multishop.dao.ProductDao;

import java.util.List;

/**
 * This class implements business logic for product model of data.
 * @author Michal Stawarski
 */
@Service("productService")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * This method finds product by its id.
     * @param productId
     * @return productDao.findById(productId)
     */
    @Override
    public Product findById(int productId) {
        return productDao.findById(productId);
    }

    /**
     * This method finds product by its name.
     * @param productName
     * @return productDao.findByName(productName)
     */
    @Override
    public Product findByName(String productName) {
        return productDao.findByName(productName);
    }

    /**
     * This method saves product.
     * @param product
     */
    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    /**
     * This method updates product.
     * @param product
     */
    @Override
    public void updateProduct(Product product) {
        Product entity = productDao.findById(product.getProductId());
        if(entity != null){
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

    /**
     * This method deletes product.
     * @param productId
     */
    @Override
    public void delProductById(int productId) {
        productDao.delProductById(productId);
    }

    /**
     * This method makes the list of all products.
     * @return productDao.findAllProducts()
     */
    @Override
    public List<Product> findAllProducts() {
        return productDao.findAllProducts();
    }

    /**
     * This method finds product by its category.
     * @param productCategory
     * @return productDao.findProductsByCategory(productCategory)
     */
    @Override
    public Product findProductsByCategory(String productCategory) {
        return productDao.findProductsByCategory(productCategory);
    }

    /**
     * This method verifies if product number is unique.
     * @param productId
     * @param productName
     * @return (product == null || (productId != null) && product.getProductId() == productId)
     */
    @Override
    public boolean isProductNumberUnique(Integer productId, String productName){
        Product product = findByName(productName);
        return (product == null || (productId != null) && product.getProductId() == productId);
    }
}
