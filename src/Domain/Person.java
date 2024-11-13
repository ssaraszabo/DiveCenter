package Domain;
import Repository.Identifiable;

public class Person implements Identifiable {
    public int id;
    private String name;
    private int age;
    private String contactInfo;

    public Person(int id, String name, int age, String contactInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Domain.Person [id=" + id + ", name=" + name + ", age=" + age + ", contactInfo=" + contactInfo + "]";
    }
}
