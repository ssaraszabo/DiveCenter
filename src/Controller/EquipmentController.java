package Controller;

import Domain.Equipment;
import Service.EquipmentService;

import java.util.Date;
import java.util.List;

public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    public Equipment createEquipment(int equipmentID, String type, int condition, Date lastMaintainanceDate) {
        return equipmentService.addEquipment(equipmentID, type, condition, lastMaintainanceDate);
    }

    public boolean updateEquipment(Equipment equipment) {
        return equipmentService.updateEquipment(equipment);
    }

    public boolean deleteEquipment(int equipmentID) {
        return equipmentService.deleteEquipment(equipmentID);
    }

    public Equipment getEquipment(int equipmentID) {
        return equipmentService.getEquipment(equipmentID);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }
}

