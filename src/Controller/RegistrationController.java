package Controller;
import Domain.Client;
import Domain.Course;
import Domain.Employee;
import Domain.Registration;
import Service.RegistrationService;

import java.util.Date;
import java.util.List;

public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public Registration addRegistration(int registrationID, Client client, Course course, int amount) {
        return registrationService.createRegistration(registrationID, client, course, amount);
    }

    public void deleteRegistration(int registrationID) {
        registrationService.deleteRegistration(registrationID);
    }

    public void modifyRegistrationTime(int registrationID, Date newTime) {
        registrationService.updateRegistration(registrationID, newTime);
    }

    public List<Registration> listRegistrations() {
        return registrationService.getAllRegistrations();
    }

    public Registration getRegistrationById(int registrationID) {
        return registrationService.getRegistrationID(registrationID);
    }
}
