package Service;
import Domain.Person;
import Domain.Client;
import Repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ClientService {
    private IRepository<Client> clientRepository;
    private IRepository<Person> personRepository;

    public ClientService(IRepository<Client> clientRepository,IRepository<Person> personRepository) {
        this.clientRepository = clientRepository;
        this.personRepository=personRepository;
        /**
         * Initializes a new instance of ClientService with a FileRepository and DBRepository.
         */
//        this.clientRepository = new FileRepository<>(
//                "clients.txt",
//                Client::getId,
//                line -> {
//                    String[] parts = line.split(",");
//                    return new Client(
//                            Integer.parseInt(parts[0]), //id
//                            parts[1],                   //name
//                            Integer.parseInt(parts[2]), //age
//                            parts[3],                   //contactInfo
//                            parts[4],                   //experienceLevel
//                            Boolean.parseBoolean(parts[5]) //isMember
//                    );
//                },
//                client -> String.join(",",
//                        String.valueOf(client.getId()),
//                        client.getName(),
//                        String.valueOf(client.getAge()),
//                        client.getContactInfo(),
//                        client.getexperienceLevel(),
//                        String.valueOf(client.isMember())
//                )
//        );
    }
    /**
     * Adds a new client to the repository.
     *
     * @param client The client to add.
     */
    public void addClient(Client client) {
        try {
            // Step 1: Insert Person into the Person table
            Person person = new Person(0, client.getName(), client.getAge(), client.getContactInfo());
            int personId = personRepository.addAndReturnGeneratedKey(person);

            // Step 2: Insert Client into the Client table with the retrieved PersonId
            Client newClient = new Client(
                    personId,                     // Use the new PersonId
                    client.getName(),
                    client.getAge(),
                    client.getContactInfo(),
                    client.getexperienceLevel(),
                    client.isMember()
            );
            clientRepository.create(newClient);

            System.out.println("Client successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error adding client: " + e.getMessage());
        }
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

    /**
     * Filters the clients to return only those with membership.
     * @return List of clients with membership.
     */
    public List<Client> filterClientsWithMembership() {
        return clientRepository.readAll().stream()
                .filter(Client::isMember) // Check if the client is a member
                .collect(Collectors.toList());
    }

    /**
     * Filters clients by experience level.
     * @param experienceLevel the experience level to filter by (ex Beginner, Advanced).
     * @return List of clients with the that experience level.
     */
    public List<Client> filterClientsByExperienceLevel(String experienceLevel) {
        return clientRepository.readAll().stream()
                .filter(client -> client.getexperienceLevel().equalsIgnoreCase(experienceLevel)) // Match experience level
                .collect(Collectors.toList());
    }
}
