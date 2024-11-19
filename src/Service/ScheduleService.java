package Service;
import Domain.Schedule;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class ScheduleService {
    private IRepository<Schedule> scheduleRepository;

    public ScheduleService(IRepository<Schedule> scheduleRepository) {

        //this.scheduleRepository = scheduleRepository;
        this.scheduleRepository = new FileRepository<>(
                "schedules.txt",
                Schedule::getScheduleID,
                line -> {
                    String[] parts = line.split(",");
                    return new Schedule(
                            Integer.parseInt(parts[0]),         //scheduleID
                            Integer.parseInt(parts[1]),         //employeeID
                            new Date(Long.parseLong(parts[2])), //startTime
                            new Date(Long.parseLong(parts[3])) //endTime
                    );
                },
                schedule -> String.join(",",
                        String.valueOf(schedule.getScheduleID()),
                        String.valueOf(schedule.getEmployeeID()),
                        String.valueOf(schedule.getStartTime().getTime()),
                        String.valueOf(schedule.getEndTime().getTime())
                )
        );
    }

    public boolean addSchedule(Schedule schedule) {
        return scheduleRepository.create(schedule);
    }

    public boolean updateSchedule(Schedule schedule) {
        return scheduleRepository.update(schedule);
    }

    public boolean deleteSchedule(int scheduleID) {
        return scheduleRepository.delete(scheduleID);
    }

    public Schedule getSchedule(int scheduleID) {
        return scheduleRepository.read(scheduleID);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.readAll();
    }
}
