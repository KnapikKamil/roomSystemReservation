package org.example;

public class Room {
   private int number;
   private BedType[] beds;

    public Room(int number, BedType[] bedTypes) {
        this.number = number;
        this.beds = bedTypes;
    }
   public String getInfo(){
        String bedInfo = "Ilość łóżek w pokoju:\n";
        for (BedType bed : beds){
            bedInfo ="\t" + bedInfo + bed + "\n";
        }
       return String.format("Dodano nowy pokój - numer %d %s", this.number, bedInfo);

   }

}
