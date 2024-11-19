package Service;

import Domain.Equipment;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.List;
import java.util.Date;

public class EquipmentService {
    private final IRepository<Equipment> equipmentRepository;

    public EquipmentService(IRepository<Equipment> equipmentRepository) {
        //this.equipmentRepository = equipmentRepository;
        this.equipmentRepository = new FileRepository<>(
                "equipment.txt",
                Equipment::getEquipmentID,
                line -> {
                    String[] parts = line.split(",");
                    return new Equipment(
                            Integer.parseInt(parts[0]), //equipmentID
                            parts[1],                   //type
                            Integer.parseInt(parts[2]), //condition
                            new Date(Long.parseLong(parts[3])) //lastMaintenanceDate(as timestamp)
                    );
                },
                equipment -> String.join(",",
                        String.valueOf(equipment.getEquipmentID()),
                        equipment.getType(),
                        String.valueOf(equipment.getCondition()),
                        String.valueOf(equipment.getLastmaintainancedate().getTime())
                )
        );
    }

    public Equipment addEquipment(int equipmentID, String type, int condition, Date lastMaintainanceDate) {
        Equipment equipment = new Equipment(equipmentID, type, condition, lastMaintainanceDate);
        equipmentRepository.create(equipment);
        return equipment;
    }

    public boolean updateEquipment(Equipment equipment) {
        return equipmentRepository.update(equipment);
    }

    public boolean deleteEquipment(int equipmentID) {
        return equipmentRepository.delete(equipmentID);
    }

    public Equipment getEquipment(int equipmentID) {
        return equipmentRepository.read(equipmentID);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.readAll();
    }
}
