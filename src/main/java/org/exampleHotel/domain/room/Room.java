package org.exampleHotel.domain.room;

import jakarta.persistence.*;
import org.exampleHotel.domain.room.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private int number;

    @ElementCollection(targetClass = BedType.class)
    private List<BedType> beds;



    public Room(long id, int number, List<BedType> bedTypes) {
        this.id = id;
        this.number = number;
        if (bedTypes==null){
            this.beds = new ArrayList<>();
        }else{
            this.beds = bedTypes;
        }
    }

    public Room(int number, List<BedType> beds) {
        this.number = number;
        this.beds = beds;
    }

    public Room() {
    }

    public long getId() {
        return id;
    }


    public String getInfo() {
        StringBuilder bedInfo = new StringBuilder("Rodzaje łóżek w pokoju:\n");
        for (BedType bed : beds) {
            bedInfo.append("\t").append(bed).append("\n");
        }
        return String.format("%d Numer: %d %s", this.id, this.number, bedInfo);
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

    public int getNumber() {
        return this.number;
    }

    void addBed(BedType bedType) {
        this.beds.add(bedType);
    }

    void setNumber(int number) {
        this.number = number;
    }

    void setBeds(List<BedType> bedTypes) {
        this.beds = bedTypes;
    }

    public List<BedType> getBeds() {
        return this.beds;
    }
}
