package Service;

import java.util.Date;
import java.util.List;
import Domain.Course;
import Repository.FileRepository;
import Repository.IRepository;

public class CourseService {
    private final IRepository<Course> courseRepository;

    public CourseService(IRepository<Course> courseRepository) {
        /**
         * Initializes a new instance of CourseService with a FileRepository.
         */
        this.courseRepository = new FileRepository<>(
                "courses.txt",
                Course::getCourseID,
                line -> {
                    String[] parts = line.split(",");
                    return new Course(
                            Integer.parseInt(parts[0]), // courseID
                            parts[1],                   // name
                            new Date(Long.parseLong(parts[2])), // startTime (as timestamp)
                            Integer.parseInt(parts[3]), // minAge
                            parts[4],                   // experienceRequired
                            Integer.parseInt(parts[5]), // maxCapacity
                            Integer.parseInt(parts[6])  // currentCapacity
                    );
                },
                course -> String.join(",",
                        String.valueOf(course.getCourseID()),
                        course.getName(),
                        String.valueOf(course.getStartTime().getTime()),
                        String.valueOf(course.getMinAge()),
                        course.getExperienceRequired(),
                        String.valueOf(course.getMaxCapacity()),
                        String.valueOf(course.getCurrentCapacity())
                )
        );
    }

    public List<Course> getAllCourses() {
        return courseRepository.readAll();
    }

    public Course getCourseById(int courseId) {
        return courseRepository.read(courseId);
    }

    /**
     * Checks if the specified course has available spots.
     *
     * @param courseId The ID of the course to check.
     * @return {@code true} if the course exists and has available spots, {@code false} otherwise.
     */
    public boolean hasAvailableSpots(int courseId) {
        Course course = courseRepository.read(courseId);
        return course.getCurrentCapacity() < course.getMaxCapacity();
    }
}


