package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.multishop.dto.ClientDTO;
import pl.multishop.model.Client;
import pl.multishop.service.ClientService;
import pl.multishop.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    private ClientService clientService;
    private OrderService orderService;

    @Autowired
    public ClientController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/allClients", method = RequestMethod.GET, produces = "text/html; charset = utf-8")
    public String getAllData(){
        List<Client> clients = clientService.findAllClients();
        List<ClientDTO> clientsDTO = new ArrayList<>();
        clients.stream().forEach(client -> {
            ClientDTO dto = new ClientDTO();
            dto.setId(client.getId());
            dto.setFirstName(client.getFirstName());
            dto.setLastName(client.getLastName());
            dto.setHomeAdress(client.getHomeAdress());
            dto.setShippingAdress(client.getShippingAdress());
            dto.setCountry(client.getCountry());
            dto.setCity(client.getCity());
            dto.setEmail(client.getEmail());
            dto.setPhone(client.getPhone());
            dto.setOrders(orderService.findAllOrders(client.getId()));
            clientsDTO.add(dto);
        });
        return "allClients.html";
    }

    @RequestMapping(value = { "/clientsList" }, method = RequestMethod.GET, produces = "text/html; charset = utf-8")
    public String clientList(ModelMap modelMap){
        List<Client> clients = clientService.findAllClients();
        modelMap.addAttribute("clients", clients);
         return "clients";
    }

    @RequestMapping(value = { "/newClient" }, method = RequestMethod.GET, produces = "text/html; charset = utf-8")
    public String newClient(ModelMap modelMap){
        Client client = new Client();
        modelMap.addAttribute("client", client);
        modelMap.addAttribute("edit",false);
        return "creatingClient";
    }
}
