package Repository;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import Domain.Course;

public class CourseRepository {
    private final List<Course> courses;

    public CourseRepository() {

        List<Course> initialCourses = new ArrayList<>();
        initialCourses.add(new Course(1, "Beginner Diving", new Date(), 10, "Beginner", 10, 0));
        initialCourses.add(new Course(2, "Advanced Diving", new Date(), 18, "Advanced", 5, 0));
        initialCourses.add(new Course(3, "Night Diving", new Date(), 16, "Intermediate", 8, 0));
        initialCourses.add(new Course(4, "Rescue Diver", new Date(), 18, "Advanced", 6, 0));
        initialCourses.add(new Course(5, "Photography Dive", new Date(), 12, "Intermediate", 7, 0));
        initialCourses.add(new Course(6, "Wreck Diving", new Date(), 18, "Advanced", 4, 0));
        initialCourses.add(new Course(7, "Open Water Diving", new Date(), 15, "Beginner", 10, 0));
        initialCourses.add(new Course(8, "Marine Biology Exploration", new Date(), 14, "Beginner", 9, 0));

        this.courses = Collections.unmodifiableList(initialCourses);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course getCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseID() == courseId) {
                return course;
            }
        }
        return null;
    }
}

