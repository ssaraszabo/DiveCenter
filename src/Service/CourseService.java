package Service;
import Domain.Equipment;
import Repository.CourseRepository;
import java.util.Date;
import java.util.List;
import Domain.Course;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course getCourseById(int courseId) {
        for (Course course : courseRepository.getAllCourses()) {
            if (course.getCourseID() == courseId) {
                return course;
            }
        }
        System.out.println("Course with ID " + courseId + " not found.");
        return null;
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public boolean hasAvailableSpots(int courseId) {
        Course course = courseRepository.getCourseById(courseId);
        return course != null && course.getCurrentCapacity() < course.getMaxCapacity();
    }
}


