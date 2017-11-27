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
 * Created by michal on 05.05.17.
 */

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String product(){
        return "product";
    }

    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    public String listProducts(ModelMap modelMap){
        List<Product> products = productService.findAllProducts();
        modelMap.addAttribute("products", products);
        return "product";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newProduct(ModelMap modelMap){
        Product product = new Product();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("edit", false);
        return "creating";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult bindingResult, ModelMap modelMap){

        if(bindingResult.hasErrors()){
            return "creating";
        }

        if(!productService.isProductNumberUnique(product.getProductId(), product.getProductName())){
            FieldError productNameError = new FieldError("product", "productName",
                    messageSource.getMessage("non.unique.productNumber", new String[]{product.getProductName()}, Locale.getDefault()));
            bindingResult.addError(productNameError);
            return "creating";
        }

        productService.saveProduct(product);
        modelMap.addAttribute("success", "Produkt numer " + product.getProductName() + " zapisany pomyślnie!");
        return "success";
    }

    @RequestMapping(value = { "/edit-{productId}-product" }, method = RequestMethod.GET)
    public String editProduct(@PathVariable int productId, ModelMap modelMap){
        Product product = productService.findById(productId);
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("edit", true);
        return "creating";
    }

    @RequestMapping(value = { "/edit-{productId}-product" }, method = RequestMethod.POST)
    public String updateProduct(@Valid Product product, BindingResult bindingResult, ModelMap modelMap){
        if(bindingResult.hasErrors()){
            return "creating";
        }

        if(!productService.isProductNumberUnique(product.getProductId(), product.getProductName())){
            FieldError productNameError = new FieldError("product", "productName",
                    messageSource.getMessage("non.unique.productNumber", new String[]{product.getProductName()}, Locale.getDefault()));
            bindingResult.addError(productNameError);
            return "creating";
        }

        productService.saveProduct(product);
        modelMap.addAttribute("success", "Produkt numer " + product.getProductName() + " zmieniony pomyślnie!");
        return "success";
    }

    @RequestMapping(value = { "/delete-{productId}-product" }, method = RequestMethod.GET)
    public String deleteProduct(@PathVariable int productId){
        productService.delProductById(productId);
        return "redirect:/list";
    }
}
