package Domain;

public class Client extends Person {
    private String experienceLevel;
    private boolean isMember;

    public Client(int id, String name, int age, String contactInfo, String experienceLevel, boolean isMember) {
        super(id, name, age, contactInfo);
        this.experienceLevel = experienceLevel;
        this.isMember = isMember;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }

    @Override
    public String toString() {
        return "Domain.Client [experienceLevel=" + experienceLevel + ", isMember=" + isMember + ", " + super.toString() + "]";
    }
}
