package Service;

import Domain.Equipment;
import Repository.IRepository;

import java.util.List;
import java.util.Date;

public class EquipmentService {
    private final IRepository<Equipment> equipmentRepository;

    public EquipmentService(IRepository<Equipment> equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
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
