package com.abc.project1.ExceptionHandling.CustomException;

public class InvalidIdException extends Exception{
    private final int id;
    public InvalidIdException(int id){
        super();
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
