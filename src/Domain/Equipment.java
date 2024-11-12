package Domain;
import java.util.Date;

public class Equipment {
    private int equipmentID;
    private String type;
    private int condition;
    private Date lastmaintainancedate;

    public Equipment(int equipmentID, String type, int condition, Date lastmaintainancedate) {
        this.equipmentID = equipmentID;
        this.type = type;
        this.condition = condition;
        this.lastmaintainancedate = lastmaintainancedate;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public Date getLastmaintainancedate() {
        return lastmaintainancedate;
    }

    public void setLastmaintainancedate(Date lastmaintainancedate) {
        this.lastmaintainancedate = lastmaintainancedate;
    }
    @Override
    public String toString() {
        return "Domain.Equipment [equipmentID=" + equipmentID + ", type=" + type + ", condition=" + condition +
                ", Date=" + lastmaintainancedate + "]";
    }
}
