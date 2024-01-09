package com.personal.carpark.service;

import com.personal.carpark.domain.CarParkSlot;
import com.personal.carpark.exception.SlotModificationNotAllowedException;
import com.personal.carpark.exception.SlotNotFoundException;
import com.personal.carpark.repository.CarParkSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarParkService {
    @Autowired
    private CarParkSlotRepository carParkSlotRepository;

    public CarParkSlot allocateSlot(String registrationNumber) throws SlotNotFoundException {
        CarParkSlot carParkSlot = new CarParkSlot();
        carParkSlot.setRegistrationNumber(registrationNumber);
        carParkSlot.setEntryTime(LocalDateTime.now());
        carParkSlot.setDuration(2); // Initial duration is 2 hours
        return carParkSlotRepository.save(carParkSlot);
    }

    public CarParkSlot amendSlot(int slotId, int additionalHours) throws SlotModificationNotAllowedException, SlotNotFoundException {
        // Implement logic to check expiration time and amend the slot
        // Update entryTime and duration accordingly
        // Don't forget to validate if the slot is still valid for modification
        CarParkSlot carParkSlot =   carParkSlotRepository.findById(slotId);
        LocalDateTime expirationTime=carParkSlot.getEntryTime().plusHours(carParkSlot.getDuration());
        if(LocalDateTime.now().isBefore(expirationTime)){
            carParkSlot.setDuration(carParkSlot.getDuration() + additionalHours);
            return carParkSlotRepository.save(carParkSlot);
        }else {
            throw new SlotModificationNotAllowedException("Slot modification not allowed after expiration time");
        }

    }

    public String deallocateSlot(int slotId) throws SlotNotFoundException {
        // Implement logic to calculate cost/charge
        // Update the database or perform any necessary actions
        // For simplicity, let's assume the charge is GBP 10 per hour
        CarParkSlot carParkSlot= carParkSlotRepository.findById(slotId);
        int duration = carParkSlot.getDuration();
        double charge = duration * 10.0;
        carParkSlotRepository.deleteById(slotId);

      return "Car deallocated from Slot:"+slotId +" Total Charge :"+charge;
    }

    public List<Integer> availableSlots() {
        return carParkSlotRepository.getSlots();
    }
}
