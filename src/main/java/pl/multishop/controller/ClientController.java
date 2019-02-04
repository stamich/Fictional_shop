package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.multishop.dto.ClientDTO;
import pl.multishop.model.Client;
import pl.multishop.service.ClientService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    // TO DO
    public String getAllData(){
        List<Client> clients = clientService.findAllClients();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        for(Client client: clients){
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(client.getId());
        }
        return "clientsAll";
    }


    @RequestMapping(value = { "/clientsList" }, method = RequestMethod.GET)
    public String clientList(ModelMap modelMap){
        List<Client> clients = clientService.findAllClients();
        modelMap.addAttribute("clients", clients);
         return "clients";
    }

    @RequestMapping(value = { "/newClient" }, method = RequestMethod.GET)
    public String newClient(ModelMap modelMap){
        Client client = new Client();
        modelMap.addAttribute("client", client);
        modelMap.addAttribute("edit",false);
        return "creatingClient";
    }
}
