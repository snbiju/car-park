package com.personal.carpark.exception;

public class SlotNotFoundException extends RuntimeException{
    public SlotNotFoundException(String message){
        super(message);
    }
}
