package org.exampleHotel.domain.room;

import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.reservation.Reservation;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.domain.room.dto.RoomDTO;
import org.exampleHotel.exceptions.WrongOptionException;
import org.exampleHotel.util.SystemUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomService {

    private RoomRepository repository = ObjectPool.getRoomRepository();
    private  ReservationService reservationService = ObjectPool.getReservationService();


    public RoomService() {
    }

    public Room createNewRoom(int number, List<String> bedTypesAsString) {
        List<BedType> bedTypes = getBedTypes(bedTypesAsString);
        return repository.createNewRoom(number, bedTypes);
    }

    public Room createNewRoom(int number, int[] bedTypesOptions) {
        List<BedType> bedTypes = getBedTypes(bedTypesOptions);
        return repository.createNewRoom(number, bedTypes);
    }

    private static List<BedType> getBedTypes(int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for (int i = 0; i < bedTypesOptions.length; i = i + 1) {
            BedType bedType;
            switch (bedTypesOptions[i]) {
                case 1:
                    bedType = BedType.SINGLE;
                    break;
                case 2:
                    bedType = BedType.DOUBLE;
                    break;
                case 3:
                    bedType = BedType.KING_SIZE;
                    break;
                default:
                    throw new WrongOptionException("Wrong option when selecting bed type");
            }
            bedTypes[i] = bedType;
        }
        return Arrays.asList(bedTypes);
    }

    public List<Room> getAllRooms() {
        return repository.getAll();

    }

    public void saveAll() {
        this.repository.saveAll();
    }

    public void readAll() {
        this.repository.readAll();
    }

    public void remove(long id) {
        this.repository.remove(id);
    }

    public Room getRoomById(long roomId) {
        return this.repository.getById(roomId);
    }

    public void edit(int id, int number, List<String> bedTypesAsString) {
        List<BedType> bedTypes = getBedTypes(bedTypesAsString);
        this.repository.edit(id, number, bedTypes);

    }

    List<BedType> getBedTypes(List<String> bedTypesAsString) {
        BedType[] bedTypes = new BedType[bedTypesAsString.size()];
        for (int i = 0; i < bedTypesAsString.size(); i++) {
            BedType bedType;
            switch (bedTypesAsString.get(i)) {
                case SystemUtils.SINGLE_BED:
                    bedType = BedType.SINGLE;
                    break;
                case SystemUtils.DOUBLE_BED:
                    bedType = BedType.DOUBLE;
                    break;
                case SystemUtils.KING_SIZE:
                    bedType = BedType.KING_SIZE;
                    break;
                default:
                    throw new WrongOptionException("Wrong option when selecting bed type");
            }
            bedTypes[i] = bedType;

        }
        return Arrays.asList(bedTypes);
    }

    public void edit(long id, int number, int[] bedTypesOptions) {
        List<BedType> bedTypes = getBedTypes(bedTypesOptions);
        this.repository.edit(id, number, bedTypes);
    }

    public List<RoomDTO> getAllAsDTO() {
        List<RoomDTO> result = new ArrayList<>();
        List<Room> allRooms = repository.getAll();
        for (Room room : allRooms) {
            RoomDTO roomDTO = room.generateDTO();
            result.add(roomDTO);
        }
        return result;
    }

    public List<Room> getAvailableRooms(LocalDate from, LocalDate to) {
        if(from == null || to == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }

        if (to.isBefore(from)){
            throw new IllegalArgumentException("End date can't be before start date");
        }

        List<Room> rooms = this.repository.getAll();
        List<Reservation> reservations = reservationService.getAllReservation();

        for (Reservation reservation : reservations){

        }

       return rooms;
    }

    public void setRepository(RoomRepository roomRepository) {
        this.repository = roomRepository;
    }

    public void setResrvationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
