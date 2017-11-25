package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import pl.multishop.model.Product;
import pl.multishop.service.ProductService;

import java.util.List;
import java.util.Map;


/**
 * Created by michal on 05.05.17.
 */

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

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
}
