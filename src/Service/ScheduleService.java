package Service;
import Domain.Schedule;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class ScheduleService {
    private IRepository<Schedule> scheduleRepository;

    public ScheduleService(IRepository<Schedule> scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
        /**
         * Initializes a new instance of ScheduleService with a FileRepository and DBRepository.
         */
//        this.scheduleRepository = new FileRepository<>(
//                "schedules.txt",
//                Schedule::getScheduleID,
//                line -> {
//                    String[] parts = line.split(",");
//                    return new Schedule(
//                            Integer.parseInt(parts[0]),         //scheduleID
//                            Integer.parseInt(parts[1]),         //employeeID
//                            new Date(Long.parseLong(parts[2])), //startTime
//                            new Date(Long.parseLong(parts[3])) //endTime
//                    );
//                },
//                schedule -> String.join(",",
//                        String.valueOf(schedule.getScheduleID()),
//                        String.valueOf(schedule.getEmployeeID()),
//                        String.valueOf(schedule.getStartTime().getTime()),
//                        String.valueOf(schedule.getEndTime().getTime())
//                )
//        );
    }
    /**
     * Adds a new schedule to the repository.
     *
     * @param schedule The schedule to add.
     */
    public boolean addSchedule(Schedule schedule) {
        return scheduleRepository.create(schedule);
    }

    /**
     * Updates an existing schedule in the repository.
     *
     * @param schedule The schedule with updated information.
     */
    public boolean updateSchedule(Schedule schedule) {
        return scheduleRepository.update(schedule);
    }

    /**
     * Deletes a schedule by its ID.
     *
     * @param scheduleID The ID of the schedule to delete.
     */
    public boolean deleteSchedule(int scheduleID) {
        return scheduleRepository.delete(scheduleID);
    }

    /**
     * Retrieves a schedule by its ID.
     *
     * @param scheduleID The ID of the schedule.
     * @return The schedule with the given ID, or null if not found.
     */
    public Schedule getSchedule(int scheduleID) {
        return scheduleRepository.read(scheduleID);
    }

    /**
     * Retrieves all schedules from the repository.
     *
     * @return A list of all schedules.
     */
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.readAll();
    }
}
