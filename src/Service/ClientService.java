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

    /**
     * Adds a new client to the repository.
     *
     * @param client The client to add.
     */
    public void addClient(Client client) {
        clientRepository.create(client);
    }

    /**
     * Retrieves a client by id.
     *
     * @param id ID of the wanted client.
     * @return Client with the specified id.
     */
    public Client getClient(int id) {
        return clientRepository.read(id);
    }

    /**
     * Retrives all clients.
     *
     * @return List of all clients.
     */
    public List<Client> getAllClients() {
        return clientRepository.readAll();
    }

    /**
     * Updates a client.
     *
     * @param client Client that is to be updated.
     */
    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    /**
     * Deletes a client.
     *
     * @param id ID of the client to be deleted.
     */
    public void deleteClient(int id) {
        clientRepository.delete(id);
    }
}
