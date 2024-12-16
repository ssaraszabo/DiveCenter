package Service;

import Domain.Equipment;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.List;
import java.util.Date;

public class EquipmentService {
    private final IRepository<Equipment> equipmentRepository;

    public EquipmentService(IRepository<Equipment> equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
        /**
         * Initializes a new instance of EquipmentService with a FileRepository and DBRepository.
         */
//        this.equipmentRepository = new FileRepository<>(
//                "equipment.txt",
//                Equipment::getEquipmentID,
//                line -> {
//                    String[] parts = line.split(",");
//                    return new Equipment(
//                            Integer.parseInt(parts[0]), //equipmentID
//                            parts[1],                   //type
//                            Integer.parseInt(parts[2]), //condition
//                            new Date(Long.parseLong(parts[3])) //lastMaintenanceDate(as timestamp)
//                    );
//                },
//                equipment -> String.join(",",
//                        String.valueOf(equipment.getEquipmentID()),
//                        equipment.getType(),
//                        String.valueOf(equipment.getCondition()),
//                        String.valueOf(equipment.getLastmaintainancedate().getTime())
//                )
//        );
    }

    /**
     * Adds new equipment to the repository.
     *
     * @param equipmentID ID of new equipment.
     * @param type Type of new equipment.
     * @param condition Condition of new equipment.
     * @param lastMaintainanceDate Last maintainance date for new equipment.
     */
    public Equipment addEquipment(int equipmentID, String type, int condition, Date lastMaintainanceDate) {
        Equipment equipment = new Equipment(equipmentID, type, condition, lastMaintainanceDate);
        equipmentRepository.create(equipment);
        return equipment;
    }

    /**
     * Updates an equipment.
     *
     * @param equipment Equipment that is to be updated.
     */
    public boolean updateEquipment(Equipment equipment) {
        return equipmentRepository.update(equipment);
    }

    /**
     * Deletes an equipment.
     *
     * @param equipmentID ID of equipment that is to be deleted.
     */
    public boolean deleteEquipment(int equipmentID) {
        return equipmentRepository.delete(equipmentID);
    }

    /**
     * Retrieves equipment by id.
     *
     * @param equipmentID ID of the wanted equipment.
     * @return The equipment with the specified id.
     */
    public Equipment getEquipment(int equipmentID) {
        return equipmentRepository.read(equipmentID);
    }

    /**
     * Retrieves all equipments.
     *
     * @return List of all equipments.
     */
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.readAll();
    }
}
