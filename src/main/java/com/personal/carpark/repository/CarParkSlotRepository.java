package com.personal.carpark.repository;

import com.personal.carpark.domain.CarParkSlot;
import com.personal.carpark.exception.SlotNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Repository
public class CarParkSlotRepository {
     List<Integer>  slots= new ArrayList<>();
    Map<Integer,CarParkSlot> slotMap = new HashMap<>();
    public CarParkSlotRepository() {
        slots=IntStream.range(1,11).boxed().collect(Collectors.toList());
    }
    public CarParkSlot save(CarParkSlot carParkSlot) throws SlotNotFoundException{
        int slotNo=0;
        if(!slots.isEmpty()){
             slotNo=slots.get(0);
                slotMap.put(slotNo,carParkSlot);
                carParkSlot.setId(slotNo);
                slots.remove(slots.get(0));
        }else {
            throw new SlotNotFoundException("Slot Not Available");
        }

        return carParkSlot;

    }
    public CarParkSlot findById(int id) throws SlotNotFoundException {
        if(slotMap.get(id)!=null){
            return slotMap.get(id);
        }else {
            throw new SlotNotFoundException("Slot Not Found");
        }
    }
    public void deleteById(int id) throws SlotNotFoundException{
        if(slotMap.get(id)!=null){
            slotMap.remove(id);
            slots.add(id);
        }else {
            throw new SlotNotFoundException("Slot Not Found");
        }
    }

    public List<Integer> getSlots() {
        return slots;
    }
}
