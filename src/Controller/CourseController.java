package Controller;
import java.util.List;
import Domain.Course;
import Service.CourseService;
import Domain.Client;

public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    public void displayAvailableCourses() {
        List<Course> courses = courseService.getAllCourses();
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);  // Assuming Course has a meaningful toString() method
        }
    }

    public void checkCourseAvailability(int courseId) {
        boolean available = courseService.hasAvailableSpots(courseId);
        if (available) {
            System.out.println("Course ID " + courseId + " has available spots.");
        } else {
            System.out.println("Course ID " + courseId + " is full.");
        }
    }

    public Course getCourse(int id) {
        return courseService.getCourseById(id);
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
}