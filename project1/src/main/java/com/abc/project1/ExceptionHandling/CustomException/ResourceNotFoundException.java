package com.abc.project1.ExceptionHandling.CustomException;

public class ResourceNotFoundException extends Exception{
    private final int id;

    public ResourceNotFoundException(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
