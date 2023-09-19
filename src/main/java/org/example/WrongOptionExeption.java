package org.example;

public class WrongOptionExeption extends RuntimeException{

    private int code = 101;

    public WrongOptionExeption(){
        super();
    }
    public WrongOptionExeption(String message){
        super(message);
    }

    public int getCode() {
        return code;
    }
}
