package com.personal.carpark.domain;


import java.time.LocalDateTime;
import java.util.Objects;

public class CarParkSlot {

    private int id;
    private String registrationNumber;
    private LocalDateTime entryTime;
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarParkSlot that = (CarParkSlot) o;
        return duration == that.duration && Objects.equals(id, that.id) && Objects.equals(registrationNumber, that.registrationNumber) && Objects.equals(entryTime, that.entryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, entryTime, duration);
    }

}
