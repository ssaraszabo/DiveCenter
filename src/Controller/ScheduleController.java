package Controller;

import Domain.Schedule;
import Service.ScheduleService;

import java.util.Date;
import java.util.List;

public class ScheduleController {
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public boolean addSchedule(int scheduleID, int employeeID, Date startTime, Date endTime) {
        if (startTime.after(endTime)) {
            System.out.println("Start time cannot be after end time.");
            return false;
        }

        Schedule schedule = new Schedule(scheduleID, employeeID, startTime, endTime);
        return scheduleService.addSchedule(schedule);
    }

    public boolean updateSchedule(int scheduleID, int employeeID, Date startTime, Date endTime) {
        if (startTime.after(endTime)) {
            System.out.println("Start time cannot be after end time.");
            return false;
        }

        Schedule schedule = new Schedule(scheduleID, employeeID, startTime, endTime);
        return scheduleService.updateSchedule(schedule);
    }

    public boolean deleteSchedule(int scheduleID) {
        return scheduleService.deleteSchedule(scheduleID);
    }

    public Schedule getSchedule(int scheduleID) {
        return scheduleService.getSchedule(scheduleID);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }
}

