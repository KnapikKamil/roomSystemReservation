package org.exampleHotel.domain.room;

import org.exampleHotel.domain.room.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final long id;
    private final int number;
    private final List<BedType> beds;

    public long getId() {
        return id;
    }

    Room(long id, int number, List<BedType> bedTypes) {
        this.id = id;
        this.number = number;
        this.beds = bedTypes;
    }

    public int getNumber() {
        return number;
    }

    public List<BedType> getBeds() {
        return beds;
    }

    public String getInfo() {
        StringBuilder bedInfo = new StringBuilder("Rodzaje łóżek w pokoju:\n");
        for (BedType bed : beds) {
            bedInfo.append("\t").append(bed).append("\n");
        }
        return String.format("%d Numer: %d %s", this.id, this.number, bedInfo.toString());
    }

    String toCSV() {
       List<String> bedsAsString = getBedsAsString();
        String bedTypes = String.join("#", bedsAsString);
        return String.format("%d,%d,%s%s", this.id, this.number, bedTypes, System.getProperty("line.separator"));
    }

    private List<String> getBedsAsString() {

        List<String> bedsAsString = new ArrayList<>();

        for (int i = 0; i < this.beds.size(); i++) {
            bedsAsString.add(this.beds.get(i).toString());
        }
        return bedsAsString;
    }


    public RoomDTO generateDTO() {

        List<String> bedsAsString = getBedsAsString();
        String bedTypes = String.join(",", bedsAsString);

        int roomSize = 0;
        for(BedType bedType : beds) {
            roomSize += bedType.getSize();
        }

        return new RoomDTO(this.id, this.number, bedTypes, beds.size(), roomSize);
    }

    public void addBed(BedType bedType) {
        this.beds.add(bedType);
    }
}
