package org.example;

public class Guest {
    private Gender gender;
   private String firstName;
   private String lastName;
   private int age;

   public  Guest(Gender gender, String firstName, String lastName, int age){
       this.gender = gender;
       this.firstName = firstName;
       this.lastName = lastName;
       this.age = age;
   }
    public String getInfo(){
        return   String.format("Dodano nowego gościa: %s %s %s (%d) ", this.gender, this.firstName, this.lastName, this.age);
    }
}
