package Console;

import Controller.MembershipController;
import Domain.Membership;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MembershipConsole {
    private MembershipController membershipController;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public MembershipConsole(MembershipController membershipController) {
        this.membershipController = membershipController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("Membership Management");
            System.out.println("1. Add Membership");
            System.out.println("2. Update Membership");
            System.out.println("3. Delete Membership");
            System.out.println("4. View Membership");
            System.out.println("5. View All Memberships");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addMembership();
                    break;
                case 2:
                    updateMembership();
                    break;
                case 3:
                    deleteMembership();
                    break;
                case 4:
                    viewMembership();
                    break;
                case 5:
                    viewAllMemberships();
                    break;
                case 6:
                    System.out.println("Exiting Membership Management.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addMembership() {
        System.out.print("Enter Membership ID: ");
        int membershipID = Integer.parseInt(scanner.nextLine());

        Date startDate = getDateFromUser("Enter Start Date (YYYY-MM-DD): ");
        if (startDate == null) return;

        Date endDate = getDateFromUser("Enter End Date (YYYY-MM-DD): ");
        if (endDate == null) return;

        System.out.print("Enter Membership Type: ");
        String membershipType = scanner.nextLine();

        if (membershipController.addMembership(membershipID, startDate, endDate, membershipType)) {
            System.out.println("Membership added successfully.");
        } else {
            System.out.println("Failed to add membership.");
        }
    }

    private void updateMembership() {
        System.out.print("Enter Membership ID to update: ");
        int membershipID = Integer.parseInt(scanner.nextLine());

        Date startDate = getDateFromUser("Enter Start Date (YYYY-MM-DD): ");
        if (startDate == null) return;

        Date endDate = getDateFromUser("Enter End Date (YYYY-MM-DD): ");
        if (endDate == null) return;

        System.out.print("Enter New Membership Type: ");
        String membershipType = scanner.nextLine();

        if (membershipController.updateMembership(membershipID, startDate, endDate, membershipType)) {
            System.out.println("Membership updated successfully.");
        } else {
            System.out.println("Failed to update membership.");
        }
    }

    private void deleteMembership() {
        System.out.print("Enter Membership ID to delete: ");
        int membershipID = Integer.parseInt(scanner.nextLine());

        if (membershipController.deleteMembership(membershipID)) {
            System.out.println("Membership deleted successfully.");
        } else {
            System.out.println("Failed to delete membership.");
        }
    }

    private void viewMembership() {
        System.out.print("Enter Membership ID to view: ");
        int membershipID = Integer.parseInt(scanner.nextLine());

        Membership membership = membershipController.getMembership(membershipID);
        if (membership != null) {
            System.out.println("Membership Details: " + membership);
        } else {
            System.out.println("Membership not found.");
        }
    }

    private void viewAllMemberships() {
        List<Membership> memberships = membershipController.getAllMemberships();
        if (memberships.isEmpty()) {
            System.out.println("No memberships available.");
        } else {
            System.out.println("All Memberships:");
            for (Membership membership : memberships) {
                System.out.println(membership);
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

