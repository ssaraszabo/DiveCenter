package Service;

import Domain.Client;
import Repository.IRepository;

import java.util.List;

public class ClientService {
    private IRepository<Client> clientRepository;

    public ClientService(IRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) {
        clientRepository.create(client);
    }

    public Client getClient(int id) {
        return clientRepository.read(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.readAll();
    }

    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    public void deleteClient(int id) {
        clientRepository.delete(id);
    }
}
