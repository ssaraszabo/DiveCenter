package Console;

import Domain.Client;
import Controller.ClientController;

import java.util.Scanner;

public class ClientConsole {
        private ClientController clientController;
        private Scanner scanner;

        public ClientConsole(ClientController clientController) {
            this.clientController = clientController;
            this.scanner = new Scanner(System.in);
        }

        public void displayMenu() {
            while (true) {
                System.out.println("Client Management System:");
                System.out.println("1. Add Client");
                System.out.println("2. View Client");
                System.out.println("3. Update Client");
                System.out.println("4. Delete Client");
                System.out.println("5. View All Clients");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addClient();
                        break;
                    case 2:
                        viewClient();
                        break;
                    case 3:
                        updateClient();
                        break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    viewAllClients();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.next();
        System.out.print("Enter client age: ");
        int age = scanner.nextInt();
        System.out.print("Enter client contact info: ");
        String contactInfo = scanner.next();
        System.out.print("Enter experience level: ");
        String experienceLevel = scanner.next();
        System.out.print("Is client a member (true/false): ");
        boolean isMember = scanner.nextBoolean();

        Client client = new Client(0, name, age, contactInfo, experienceLevel, isMember);
        clientController.addClient(client);
        System.out.println("Client added successfully.");
    }

    private void viewClient() {
        System.out.print("Enter client ID: ");
        int id = scanner.nextInt();
        Client client = clientController.getClient(id);
        if (client != null) {
            System.out.println("Client details: " + client);
        } else {
            System.out.println("Client not found.");
        }
    }

    private void updateClient() {
        System.out.print("Enter client ID to update: ");
        int id = scanner.nextInt();

        Client existingClient = clientController.getClient(id);     //get existing client details
        if (existingClient == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.println("Updating client details. Leave blank to keep current value.");

        System.out.print("Enter new name (current: " + existingClient.getName() + "): ");       //update Name
        scanner.nextLine(); //consume newline
        String name = scanner.nextLine();
        if (!name.isBlank()) {
            existingClient.setName(name);
        }

        System.out.print("Enter new age (current: " + existingClient.getAge() + "): ");
        String ageInput = scanner.nextLine();               //update Age
        if (!ageInput.isBlank()) {
            try {
                int age = Integer.parseInt(ageInput);
                existingClient.setAge(age);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input. Keeping current age.");
            }
        }

        System.out.print("Enter new contact info (current: " + existingClient.getContactInfo() + "): ");
        String contactInfo = scanner.nextLine();                    //update Contact Info
        if (!contactInfo.isBlank()) {
            existingClient.setContactInfo(contactInfo);
        }

        System.out.print("Enter new experience level (current: " + existingClient.getexperienceLevel() + "): ");
        String experienceLevel = scanner.nextLine();                //update Experience Level
        if (!experienceLevel.isBlank()) {
            existingClient.setExperienceLevel(experienceLevel);
        }

        System.out.print("Is client a member (current: " + existingClient.isMember() + ", enter true/false): ");
        String isMemberInput = scanner.nextLine();                  //update Membership Status
        if (!isMemberInput.isBlank()) {
            boolean isMember = Boolean.parseBoolean(isMemberInput);
            existingClient.setMember(isMember);
        }

        clientController.updateClient(existingClient);              //pass updated client to controller
        System.out.println("Client updated successfully.");
    }


    private void deleteClient() {
            System.out.print("Enter client ID to delete: ");
            int id = scanner.nextInt();
            clientController.deleteClient(id);
            System.out.println("Client deleted successfully.");
        }

        private void viewAllClients() {
            clientController.getAllClients().forEach(System.out::println);
        }


}
