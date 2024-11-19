package Service;
import Repository.CourseRepository;

import java.util.List;
import Domain.Course;
import Repository.IRepository;

public class CourseService {
    private final IRepository<Course> courseRepository;

    public CourseService(IRepository<Course> courseRepository) {
        this.courseRepository = courseRepository;
    }

//    public Course getCourseById(int courseId) {
//        for (Course course : courseRepository.getAllCourses()) {
//            if (course.getCourseID() == courseId) {
//                return course;
//            }
//        }
//        System.out.println("Course with ID " + courseId + " not found.");
//        return null;
//    }
//
//    public List<Course> getAllCourses() {
//        return courseRepository.getAllCourses();
//    }
//
//    public boolean hasAvailableSpots(int courseId) {
//        Course course = courseRepository.getCourseById(courseId);
//        return course != null && course.getCurrentCapacity() < course.getMaxCapacity();
//    }
    public List<Course> getAllCourses() {
        return courseRepository.readAll();
    }

    public Course getCourseById(int courseId) {
        return courseRepository.read(courseId);
    }
}


