package Console;

import Controller.EquipmentController;
import Domain.Equipment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EquipmentConsole {
    private final EquipmentController equipmentController;
    private final Scanner scanner;
    private final SimpleDateFormat dateFormat;

    public EquipmentConsole(EquipmentController equipmentController) {
        this.equipmentController = equipmentController;
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void showMenu() {
        while (true) {
            System.out.println("Equipment Management");
            System.out.println("1. Add Equipment");
            System.out.println("2. Update Equipment");
            System.out.println("3. Delete Equipment");
            System.out.println("4. View Equipment");
            System.out.println("5. View All Equipment");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addEquipment();
                    break;
                case 2:
                    updateEquipment();
                    break;
                case 3:
                    deleteEquipment();
                    break;
                case 4:
                    viewEquipment();
                    break;
                case 5:
                    viewAllEquipment();
                    break;
                case 6:
                    System.out.println("Exiting Equipment Management.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addEquipment() {
        System.out.print("Enter Equipment ID: ");
        int equipmentID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Type: ");
        String type = scanner.nextLine();

        System.out.print("Enter Condition (1-10): ");
        int condition = Integer.parseInt(scanner.nextLine());

        Date lastMaintainanceDate = getDateFromUser("Enter Last Maintenance Date (YYYY-MM-DD): ");
        if (lastMaintainanceDate == null) return;

        Equipment equipment = equipmentController.createEquipment(equipmentID, type, condition, lastMaintainanceDate);
        System.out.println("Equipment added successfully: " + equipment);
    }

    private void updateEquipment() {
        System.out.print("Enter Equipment ID to update: ");
        int equipmentID = Integer.parseInt(scanner.nextLine());

        Equipment existingEquipment = equipmentController.getEquipment(equipmentID);
        if (existingEquipment == null) {
            System.out.println("Equipment not found.");
            return;
        }

        System.out.print("Enter New Type: ");
        String type = scanner.nextLine();

        System.out.print("Enter New Condition (1-10): ");
        int condition = Integer.parseInt(scanner.nextLine());

        Date lastMaintainanceDate = getDateFromUser("Enter New Last Maintenance Date (YYYY-MM-DD): ");
        if (lastMaintainanceDate == null) return;

        existingEquipment.setType(type);
        existingEquipment.setCondition(condition);
        existingEquipment.setLastmaintainancedate(lastMaintainanceDate);

        if (equipmentController.updateEquipment(existingEquipment)) {
            System.out.println("Equipment updated successfully.");
        } else {
            System.out.println("Failed to update equipment.");
        }
    }

    private void deleteEquipment() {
        System.out.print("Enter Equipment ID to delete: ");
        int equipmentID = Integer.parseInt(scanner.nextLine());

        if (equipmentController.deleteEquipment(equipmentID)) {
            System.out.println("Equipment deleted successfully.");
        } else {
            System.out.println("Failed to delete equipment.");
        }
    }

    private void viewEquipment() {
        System.out.print("Enter Equipment ID to view: ");
        int equipmentID = Integer.parseInt(scanner.nextLine());

        Equipment equipment = equipmentController.getEquipment(equipmentID);
        if (equipment != null) {
            System.out.println("Equipment Details: " + equipment);
        } else {
            System.out.println("Equipment not found.");
        }
    }

    private void viewAllEquipment() {
        List<Equipment> equipmentList = equipmentController.getAllEquipment();
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment available.");
        } else {
            System.out.println("All Equipment:");
            for (Equipment equipment : equipmentList) {
                System.out.println(equipment);
            }
        }
    }

    private Date getDateFromUser(String prompt) {
        System.out.print(prompt);
        try {
            return dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return null;
        }
    }
}

