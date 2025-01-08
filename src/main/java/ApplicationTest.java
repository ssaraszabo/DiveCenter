//import Domain.*;
//import Repository.DBRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.*;
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.*;
//import java.util.*;
//import java.util.Date;
//import java.util.function.Function;
//
//    public class ApplicationTest {
//        private DBRepository<Client> clientRepository;
//        private DBRepository<Employee> employeeRepository;
//        private DBRepository<Registration> registrationRepository;
//
//        private static final String CONNECTION_STRING = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databaseName=DiveCenterDB;integratedSecurity=true;encrypt=false;";
//
//        @Before
//        public void setUp() {
//            Function<ResultSet, Client> clientMapper = rs -> {
//                try {
//                    return new Client(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getInt("age"),
//                            rs.getString("contactInfo"),
//                            rs.getString("experienceLevel"),
//                            rs.getBoolean("isMember")
//                    );
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            };
//
//            Function<Client, Object[]> clientToColumns = client -> new Object[]{
//                    client.getId(),
//                    client.getName(),
//                    client.getAge(),
//                    client.getContactInfo(),
//                    client.getexperienceLevel(),
//                    client.isMember()
//            };
//
//            clientRepository = new DBRepository<>(CONNECTION_STRING, "Client", clientMapper, clientToColumns, Client::getId);
//
//            Function<ResultSet, Employee> employeeMapper = rs -> {
//                try {
//                    return new Employee(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getInt("age"),
//                            rs.getString("contactInfo"),
//                            rs.getString("position"),
//                            rs.getString("employmentDate")
//                    );
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            };
//
//            Function<Employee, Object[]> employeeToColumns = employee -> new Object[]{
//                    employee.getId(),
//                    employee.getName(),
//                    employee.getAge(),
//                    employee.getContactInfo(),
//                    employee.getPosition(),
//                    employee.getEmploymentDate()
//            };
//
//            employeeRepository = new DBRepository<>(CONNECTION_STRING, "Employee", employeeMapper, employeeToColumns, Employee::getId);
//
//            Function<ResultSet, Registration> registrationMapper = rs -> {
//                try {
//                    // Simplified mapping for demo purposes
//                    return new Registration(
//                            rs.getInt("registrationID"),
//                            rs.getDate("registrationDate"),
//                            rs.getString("status"),
//                            new Client(
//                                    rs.getInt("clientID"),
//                                    rs.getString("clientName"),
//                                    rs.getInt("clientAge"),
//                                    rs.getString("clientContactInfo"),
//                                    rs.getString("clientExperienceLevel"),
//                                    rs.getBoolean("clientIsMember")
//                            ),
//                            new Course(
//                                    rs.getInt("courseID"),
//                                    rs.getString("courseName"),
//                                    rs.getDate("courseStartTime"),
//                                    rs.getInt("courseMinAge"),
//                                    rs.getString("courseExperienceRequired"),
//                                    rs.getInt("courseMaxCapacity"),
//                                    rs.getInt("courseCurrentCapacity")
//                            ),
//                            new Invoice(
//                                    rs.getInt("invoiceID"),
//                                    rs.getInt("amount"),
//                                    rs.getBoolean("payed"),
//                                    rs.getDate("issueDate")
//                            )
//                    );
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            };
//
//            Function<Registration, Object[]> registrationToColumns = registration -> new Object[]{
//                    registration.getRegistrationID(),
//                    new java.sql.Date(registration.getRegistrationDate().getTime()),
//                    registration.getStatus()
//            };
//
//            registrationRepository = new DBRepository<>(CONNECTION_STRING, "Registration", registrationMapper, registrationToColumns, Registration::getRegistrationID);
//        }
//
//        @Test
//        public void testCreateClient() {
//            Client client = new Client(1, "John Doe", 25, "123456789", "Advanced", true);
//            assertTrue(clientRepository.create(client));
//
//            Client fetched = clientRepository.read(1);
//            assertNotNull(fetched);
//            assertEquals("John Doe", fetched.getName());
//        }
//
//        @Test
//        public void testReadAllClients() {
//            List<Client> clients = clientRepository.readAll();
//            assertNotNull(clients);
//        }
//
//        @Test
//        public void testUpdateClient() {
//            Client client = new Client(1, "John Doe", 25, "123456789", "Advanced", true);
//            clientRepository.create(client);
//
//            client.setName("Jane Doe");
//            assertTrue(clientRepository.update(client));
//
//            Client updated = clientRepository.read(1);
//            assertEquals("Jane Doe", updated.getName());
//        }
//
//        @Test
//        public void testDeleteClient() {
//            Client client = new Client(1, "John Doe", 25, "123456789", "Advanced", true);
//            clientRepository.create(client);
//
//            assertTrue(clientRepository.delete(1));
//            assertNull(clientRepository.read(1));
//        }
//
//        @Test
//        public void testAddRegistrationValid() {
//            Client client = new Client(1, "John Doe", 25, "123456789", "Advanced", true);
//            clientRepository.create(client);
//            Invoice invoice = new Invoice(1, 250, true, new Date());
//
//            Course course = new Course(1, "Diving 101", new Date(), 18, "Beginner", 10, 5);
//            Registration registration = new Registration(1, new Date(), "Active", client, course, invoice);
//            assertTrue(registrationRepository.create(registration));
//
//            Registration fetched = registrationRepository.read(1);
//            assertNotNull(fetched);
//            assertEquals(1, fetched.getRegistrationID());
//        }
//
//        @Test
//        public void testAddRegistrationInvalid() {
//            Client invalidClient = null; // Invalid client
//            Invoice invoice = new Invoice(1, 250, true, new Date());
//            Course course = new Course(1, "Diving 101", new Date(), 18, "Beginner", 10, 5);
//            assertThrows(NullPointerException.class, () -> {
//                new Registration(1, new Date(), "Active", invalidClient, course, invoice);
//            });
//        }
//
//        @Test
//        public void testAddRegistrationLogicalCriteria() {
//            Client client = new Client(1, "John Doe", 17, "123456789", "Beginner", true); // Below minimum age
//            clientRepository.create(client);
//
//            Course course = new Course(1, "Open Water", new Date(), 18, "Beginner", 10, 10); // No available space
//
//            assertThrows(IllegalArgumentException.class, () -> {
//                if (client.getAge() < course.getMinAge()) {
//                    throw new IllegalArgumentException("Client does not meet the minimum age requirement.");
//                }
//                if (!course.hasAvailability()) {
//                    throw new IllegalArgumentException("No available space in the course.");
//                }
//                Invoice invoice = new Invoice(1, 250, true, new Date());
//                Registration registration = new Registration(1, new Date(), "Active", client, course, invoice);
//                registrationRepository.create(registration);
//            });
//        }
//    }
