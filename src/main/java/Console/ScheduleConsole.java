package Console;

import Controller.ScheduleController;
import Domain.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ScheduleConsole {
    private ScheduleController scheduleController;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public ScheduleConsole(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public void showMenu() {
        while (true) {
            System.out.println("Schedule Management");
            System.out.println("1. Add Schedule");
            System.out.println("2. Update Schedule");
            System.out.println("3. Delete Schedule");
            System.out.println("4. View Schedule");
            System.out.println("5. View All Schedules");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addSchedule();
                    break;
                case 2:
                    updateSchedule();
                    break;
                case 3:
                    deleteSchedule();
                    break;
                case 4:
                    viewSchedule();
                    break;
                case 5:
                    viewAllSchedules();
                    break;
                case 6:
                    System.out.println("Exiting Schedule Management.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addSchedule() {
        System.out.print("Enter Schedule ID: ");
        int scheduleID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Employee ID: ");
        int employeeID = Integer.parseInt(scanner.nextLine());

        Date startTime = getDateFromUser("Enter Start Time (YYYY-MM-DD HH:MM): ");
        if (startTime == null) return;

        Date endTime = getDateFromUser("Enter End Time (YYYY-MM-DD HH:MM): ");
        if (endTime == null) return;

        if (scheduleController.addSchedule(scheduleID, employeeID, startTime, endTime)) {
            System.out.println("Schedule added successfully.");
        } else {
            System.out.println("Failed to add schedule.");
        }
    }

    private void updateSchedule() {
        System.out.print("Enter Schedule ID to update: ");
        int scheduleID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Employee ID: ");
        int employeeID = Integer.parseInt(scanner.nextLine());

        Date startTime = getDateFromUser("Enter New Start Time (YYYY-MM-DD HH:MM): ");
        if (startTime == null) return;

        Date endTime = getDateFromUser("Enter New End Time (YYYY-MM-DD HH:MM): ");
        if (endTime == null) return;

        if (scheduleController.updateSchedule(scheduleID, employeeID, startTime, endTime)) {
            System.out.println("Schedule updated successfully.");
        } else {
            System.out.println("Failed to update schedule.");
        }
    }

    private void deleteSchedule() {
        System.out.print("Enter Schedule ID to delete: ");
        int scheduleID = Integer.parseInt(scanner.nextLine());

        if (scheduleController.deleteSchedule(scheduleID)) {
            System.out.println("Schedule deleted successfully.");
        } else {
            System.out.println("Failed to delete schedule.");
        }
    }

    private void viewSchedule() {
        System.out.print("Enter Schedule ID to view: ");
        int scheduleID = Integer.parseInt(scanner.nextLine());

        Schedule schedule = scheduleController.getSchedule(scheduleID);
        if (schedule != null) {
            System.out.println("Schedule Details: " + schedule);
        } else {
            System.out.println("Schedule not found.");
        }
    }

    private void viewAllSchedules() {
        List<Schedule> schedules = scheduleController.getAllSchedules();
        if (schedules.isEmpty()) {
            System.out.println("No schedules available.");
        } else {
            System.out.println("All Schedules:");
            for (Schedule schedule : schedules) {
                System.out.println(schedule);
            }
        }
    }

    private Date getDateFromUser(String prompt) {
        System.out.print(prompt);
        try {
            return dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD HH:MM.");
            return null;
        }
    }
}
