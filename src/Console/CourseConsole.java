package Console;

import Controller.CourseController;
import Domain.Course;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.List;

public class CourseConsole {
    private final CourseController courseController;
    private final Scanner scanner;
    private final SimpleDateFormat dateFormat;

    public CourseConsole(CourseController courseController) {
        this.courseController = courseController;
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void showMenu() {
        while (true) {
            System.out.println("Course Management");
            System.out.println("1. View Course");
            System.out.println("2. View All Courses");
            System.out.println("3. View Courses sorted by free Slots");
            System.out.println("4. View Courses sorted by Age");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    viewCourse();
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    viewCoursebyAge();
                    break;
                case 4:
                    viewCoursebySlots();
                    break;
                case 5:
                    System.out.println("Exiting Course Management.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewCoursebySlots() {
        List<Course> sortcourses = courseController.getCoursebyAge();
        if (sortcourses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Courses Sorted by Minimum Age:");
            for (Course course : sortcourses) {
                System.out.println(course);
            }
        }
    }

    private void viewCoursebyAge() {
        List<Course> sortedcourses = courseController.getCoursebySlots();
        if (sortedcourses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Courses Sorted by free Slots:");
            for (Course course : sortedcourses) {
                System.out.println(course);
            }
        }
    }

    private void viewCourse() {
        System.out.print("Enter Course ID to view: ");
        int courseID = Integer.parseInt(scanner.nextLine());

        Course course = courseController.getCourse(courseID);
        if (course != null) {
            System.out.println("Course Details: " + course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private void viewAllCourses() {
        List<Course> courseList = courseController.getAllCourses();
        if (courseList.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("All Courses:");
            for (Course course : courseList) {
                System.out.println(course);
            }
        }
    }
}