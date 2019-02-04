package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.multishop.model.Media;
import pl.multishop.service.MediaService;
import pl.multishop.service.ProductService;

import java.util.List;

@Controller
public class MediaController {

    private MediaService mediaService;
    private ProductService productService;

    @Autowired
    public MediaController(MediaService mediaService, ProductService productService) {
        this.mediaService = mediaService;
        this.productService = productService;
    }

    @RequestMapping(value = "/media", method = RequestMethod.GET, produces = "text/html")
    public String getAll(ModelMap modelMap){
        List<Media> medias = mediaService.findAll();
        modelMap.addAttribute("medias", medias);
        return "media.html";
    }
}
