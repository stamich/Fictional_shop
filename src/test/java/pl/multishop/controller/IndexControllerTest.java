package pl.multishop.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import pl.multishop.controller.IndexController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class IndexControllerTest {

    @Test
    public void indexPage() throws Exception{
        IndexController indexController = new IndexController();
        MockMvc mockMvc = standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).andExpect(view().name("welcome"));
    }

    @Test
    public void productPage() throws Exception{
        IndexController indexController = new IndexController();
        MockMvc mockMvc = standaloneSetup(indexController).build();
        mockMvc.perform(get("/productView")).andExpect(view().name("product"));
    }

}
