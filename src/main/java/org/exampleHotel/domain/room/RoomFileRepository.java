package org.exampleHotel.domain.room;

import org.exampleHotel.exceptions.PersistenceToFileException;
import org.exampleHotel.util.SystemUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomFileRepository implements RoomRepository {
    private final List<Room> rooms = new ArrayList<>();
    private final static RoomFileRepository instance = new RoomFileRepository();

    private RoomFileRepository(){

    }

    public static RoomFileRepository getInstance() {
        return instance;
    }

    public Room createNewRoom(int number, List<BedType> bedTypes) {


        Room newRoom = new Room(findNewId(), number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    Room addExistingRoom(long id, int number, List<BedType> bedTypes) {


        Room newRoom = new Room(id, number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public void saveAll() {
        String name = "rooms.csv";

        Path file = Paths.get(SystemUtils.DATA_DIRECTORY.toString(), name);

        StringBuilder sb = new StringBuilder("");

        for (Room room : this.rooms) {
            sb.append(room.toCSV());
        }

        try {
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "save", "room data");
        }
    }

    @Override
    public void readAll() {
        String name = "rooms.csv";

        Path file = Paths.get(SystemUtils.DATA_DIRECTORY.toString(), name);

        if(!Files.exists(file)) {
            return;
        }

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] roomsAsString = data.split(System.getProperty("line.separator"));

            for (String guestAsString : roomsAsString) {
                String[] roomData = guestAsString.split(",");
                if (roomData[0] == null || roomData[0].trim().isEmpty()){
                    continue;
                }
                int id = Integer.parseInt(roomData[0]);
                int number = Integer.parseInt(roomData[1]);
                String bedTypesData = roomData[2];
                String[] bedsTypesAsString = bedTypesData.split("#");
                BedType[] bedTypes = new BedType[bedsTypesAsString.length];
                for (int i = 0; i < bedTypes.length; i++) {

                    if(bedsTypesAsString[i].equals(SystemUtils.SINGLE_BED)) {
                        bedTypes[i] = BedType.SINGLE;
                    } else if(bedsTypesAsString[i].equals(SystemUtils.DOUBLE_BED)) {
                        bedTypes[i] = BedType.DOUBLE;
                    } else if(bedsTypesAsString[i].equals(SystemUtils.KING_SIZE)) {
                        bedTypes[i] = BedType.KING_SIZE;
                    }
                }
                addExistingRoom(id, number, Arrays.asList(bedTypes));
            }

        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "read", "room data");
        }

    }

    private long findNewId() {
        long max = 0;
        for (Room room : this.rooms) {
            if (room.getId() > max) {
                max = room.getId();
            }
        }
        return max + 1;
    }

    @Override
    public void remove(long id) {
        int roomToBeRemovedIndex = -1;
        for(int i=0;i<this.rooms.size();i++) {
            if(this.rooms.get(i).getId() == id) {
                roomToBeRemovedIndex = i;
                break;
            }
        }
        if(roomToBeRemovedIndex>-1) {
            this.rooms.remove(roomToBeRemovedIndex);
        }
    }


    @Override
    public void edit(long id, int number, List<BedType> bedTypes) {
        this.remove(id);
        this.addExistingRoom(id, number, bedTypes);
    }

    @Override
    public Room getById(long id) {
        for (Room room : this.rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }
}