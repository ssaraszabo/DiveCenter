package Controller;

import Domain.Client;
import Service.ClientService;

import java.util.List;

public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addClient(Client client) {
        clientService.addClient(client);
    }

    public Client getClient(int id) {
        return clientService.getClient(id);
    }

    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    public void updateClient(Client client) {
        clientService.updateClient(client);
    }

    public void deleteClient(int id) {
        clientService.deleteClient(id);
    }

    public List<Client> getClientsbyExp(String exp) {
        return clientService.filterClientsByExperienceLevel(exp);
    }

    public List<Client> getClientsbyMembership() {
        return clientService.filterClientsWithMembership();
    }
}
