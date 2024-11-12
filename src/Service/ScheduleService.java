package Service;
import Domain.Schedule;
import Repository.IRepository;

import java.util.List;

public class ScheduleService {
    private IRepository<Schedule> scheduleRepository;

    public ScheduleService(IRepository<Schedule> scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
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
