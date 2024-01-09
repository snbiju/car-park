package com.personal.carpark.exception;

public class SlotModificationNotAllowedException extends RuntimeException{
    public SlotModificationNotAllowedException(String message){
        super(message);
    }
}
