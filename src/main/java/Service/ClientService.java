package Service;

import Domain.Person;
import Domain.Client;
import Repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ClientService {
    private final IRepository<Client> clientRepository;
    private final IRepository<Person> personRepository;

    public ClientService(IRepository<Client> clientRepository, IRepository<Person> personRepository) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
    }

    /**
     * Adds a new client to the repository.
     *
     * @param client The client to add.
     */
    public void addClient(Client client) {
        try {
            // Directly add the client to the Clients table
            clientRepository.create(client); // This will include inherited fields from Person
            System.out.println("Client successfully added!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error adding client: " + e.getMessage());
        }
    }


    /**
     * Retrieves a client by ID.
     *
     * @param id ID of the client to retrieve.
     * @return The client with the specified ID.
     */
    public Client getClient(int id) {
        try {
            Client client = clientRepository.read(id);
            if (client != null) {
                Person person = personRepository.read(client.getId());
                if (person != null) {
                    // Sync Person details into Client
                    client.setName(person.getName());
                    client.setAge(person.getAge());
                    client.setContactInfo(person.getContactInfo());
                }
            }
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error retrieving client: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all clients.
     *
     * @return List of all clients.
     */
    public List<Client> getAllClients() {
        try {
            List<Client> clients = clientRepository.readAll();
            for (Client client : clients) {
                Person person = personRepository.read(client.getId());
                if (person != null) {
                    // Sync Person details into Client
                    client.setName(person.getName());
                    client.setAge(person.getAge());
                    client.setContactInfo(person.getContactInfo());
                }
            }
            return clients;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error retrieving all clients: " + e.getMessage());
        }
        return List.of();
    }

    /**
     * Updates a client.
     *
     * @param client The client to update.
     */
    public void updateClient(Client client) {
        try {
            // Step 1: Update the Person details.
            Person person = new Person(client.getId(), client.getName(), client.getAge(), client.getContactInfo());
            personRepository.update(person);

            // Step 2: Update the Client-specific details.
            clientRepository.update(client);

            System.out.println("Client successfully updated!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error updating client: " + e.getMessage());
        }
    }

    /**
     * Deletes a client.
     *
     * @param id The ID of the client to delete.
     */
    public void deleteClient(int id) {
        try {
            // Step 1: Delete the Client entry.
            clientRepository.delete(id);

            // Step 2: Delete the Person entry.
            personRepository.delete(id);

            System.out.println("Client successfully deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error deleting client: " + e.getMessage());
        }
    }

    /**
     * Filters the clients to return only those with membership.
     *
     * @return List of clients with membership.
     */
    public List<Client> filterClientsWithMembership() {
        return getAllClients().stream()
                .filter(Client::isMember)
                .collect(Collectors.toList());
    }

    /**
     * Filters clients by experience level.
     *
     * @param experienceLevel The experience level to filter by (e.g., Beginner, Advanced).
     * @return List of clients with the specified experience level.
     */
    public List<Client> filterClientsByExperienceLevel(String experienceLevel) {
        return getAllClients().stream()
                .filter(client -> client.getexperienceLevel().equalsIgnoreCase(experienceLevel))
                .collect(Collectors.toList());
    }
}
