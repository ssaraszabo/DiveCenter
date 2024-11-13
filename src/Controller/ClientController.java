package Controller;

import Console.*;
import Domain.*;
import Repository.CourseRepository;
import Repository.InMemoryRepository;
import Service.*;

import java.util.List;
import java.util.Scanner;

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
}

