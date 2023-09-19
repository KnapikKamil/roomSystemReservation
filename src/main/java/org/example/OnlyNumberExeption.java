package org.example;


public class OnlyNumberExeption extends RuntimeException {

    private int code = 102;

    public OnlyNumberExeption(){
        super();
    }
    public OnlyNumberExeption(String messege){
        super(messege);
    }

    public int getCode() {
        return code;
    }
}
