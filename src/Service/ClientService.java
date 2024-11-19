package Service;

import Domain.Client;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.List;

public class ClientService {
    private IRepository<Client> clientRepository;

    public ClientService(IRepository<Client> clientRepository) {
        //this.clientRepository = clientRepository;
        /**
         * Initializes a new instance of ClientService with a FileRepository.
         */
        this.clientRepository = new FileRepository<>(
                "clients.txt",
                Client::getId,
                line -> {
                    String[] parts = line.split(",");
                    return new Client(
                            Integer.parseInt(parts[0]), //id
                            parts[1],                   //name
                            Integer.parseInt(parts[2]), //age
                            parts[3],                   //contactInfo
                            parts[4],                   //experienceLevel
                            Boolean.parseBoolean(parts[5]) //isMember
                    );
                },
                client -> String.join(",",
                        String.valueOf(client.getId()),
                        client.getName(),
                        String.valueOf(client.getAge()),
                        client.getContactInfo(),
                        client.getexperienceLevel(),
                        String.valueOf(client.isMember())
                )
        );
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
