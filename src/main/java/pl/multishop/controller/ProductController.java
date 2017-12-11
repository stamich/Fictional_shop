package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import pl.multishop.model.Product;
import pl.multishop.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * This class of presentation layer provides product controller.
 * @author Michal Stawarski
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/multishop", method = RequestMethod.GET)
    public String product(){
        return "/customer/product";
    }

    /**
     * This method lists all exisiting products (REST - GET).
     * @param modelMap
     * @return products
     */
    @RequestMapping(value = { "/productsList" }, method = RequestMethod.GET)
    public String listProducts(ModelMap modelMap){
        List<Product> products = productService.findAllProducts();
        modelMap.addAttribute("products", products);
        return "products";
    }

    /**
     * This method creates the new product (REST - GET).
     * @param modelMap
     * @return addProduct
     */
    @RequestMapping(value = { "/newProduct" }, method = RequestMethod.GET)
    public String newProduct(ModelMap modelMap){
        Product product = new Product();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("edit", false);
        return "addProduct";
    }

    /**
     * This method saves created product (REST - POST).
     * @param product
     * @param bindingResult
     * @param modelMap
     * @return success
     */
    @RequestMapping(value = { "/newProduct" }, method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult bindingResult, ModelMap modelMap){

        if(bindingResult.hasErrors()){
            return "addProduct";
        }

        if(!productService.isProductNumberUnique(product.getProductId(), product.getProductName())){
            FieldError productNameError = new FieldError("product", "productName",
                    messageSource.getMessage("non.unique.productNumber", new String[]{product.getProductName()}, Locale.getDefault()));
            bindingResult.addError(productNameError);
            return "addProduct";
        }

        productService.saveProduct(product);
        modelMap.addAttribute("success", "Produkt numer " + product.getProductId() + " zapisany pomyślnie!");
        return "success";
    }

    /**
     * This method allows to edit product properties (REST - GET).
     * @param productId
     * @param modelMap
     * @return addProduct
     */
    @RequestMapping(value = { "/edit-{productId}-product" }, method = RequestMethod.GET)
    public String editProduct(@PathVariable int productId, ModelMap modelMap){
        Product product = productService.findById(productId);
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("edit", true);
        return "addProduct";
    }

    /**
     * This method allows to update product properties (REST - POST).
     * @param product
     * @param bindingResult
     * @param modelMap
     * @param productId
     * @return success
     */
    @RequestMapping(value = { "/edit-{productId}-product" }, method = RequestMethod.POST)
    public String updateProduct(@Valid Product product, BindingResult bindingResult, ModelMap modelMap, @PathVariable int productId){

        if(bindingResult.hasErrors()){
            return "addProduct";
        }

        if(!productService.isProductNumberUnique(product.getProductId(), product.getProductName())){
            FieldError productNameError = new FieldError("product", "productName",
                    messageSource.getMessage("non.unique.productNumber", new String[]{product.getProductName()}, Locale.getDefault()));
            bindingResult.addError(productNameError);
            return "addProduct";
        }

        productService.saveProduct(product);
        modelMap.addAttribute("success", "Produkt numer " + product.getProductId() + " zmieniony pomyślnie!");
        return "success";
    }

    /**
     * This method deletes existing order by it id number (REST - GET).
     * @param productId
     * @return redirect:/list
     */
    @RequestMapping(value = { "/delete-{productId}-product" }, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable int productId){
        productService.delProductById(productId);
        return "redirect:/list";
    }
}
