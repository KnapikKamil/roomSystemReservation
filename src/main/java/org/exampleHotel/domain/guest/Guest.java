package org.exampleHotel.domain.guest;


import jakarta.persistence.*;
import org.exampleHotel.domain.guest.dto.GuestDTO;



@Entity

public class Guest {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
   private  String firstName;
   private  String lastName;
   private int age;
   @Enumerated
    private  Gender gender;

    public long getId() {
        return id;
    }

    public Guest(long id, String firstName, String lastName, int age, Gender gender){
        this.id = id;
       this.firstName = firstName;
       this.lastName = lastName;
       this.age = age;
       this.gender = gender;
   }

    public Guest(String firstName, String lastName, int age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public Guest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getInfo(){
        return   String.format("%d %s %s (%d) %s ",this.id, this.firstName, this.lastName, this.age, this.gender);
    }
    String toCSV(){
       return String.format("%d,%s,%s,%d,%s%s",this.id, this.firstName, this.lastName, this.age, this.gender, System.getProperty("line.separator"));
    }

    public GuestDTO getAsDTO() {
        String gender = "Nie binarna";
        if (this.gender.equals(Gender.FEMALE)){
            gender = "Kobieta";
        } else if (this.gender.equals(Gender.MALE)) {
            gender = "Mężczyzna";
        } else if (this.gender.equals(Gender.LGBTQ)) {
            gender = "Nie binarna";
        }
        return new GuestDTO(this.id, this.firstName, this.lastName, this.age, gender);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
