package com.personal.carpark.controller;

import com.personal.carpark.domain.CarParkSlot;
import com.personal.carpark.exception.SlotNotFoundException;
import com.personal.carpark.service.CarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carpark")
public class CarParkController {
    @Autowired
    private CarParkService carParkService;

    @PostMapping(path="/allocate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CarParkSlot> allocateSlot(@RequestBody CarParkSlot carParkSlot) throws SlotNotFoundException {
        CarParkSlot response = carParkService.allocateSlot(carParkSlot.getRegistrationNumber());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping ("/available")
    public ResponseEntity<List<Integer>> getSlots() {
        List<Integer> availableSlot = carParkService.availableSlots();
        return new ResponseEntity<>(availableSlot, HttpStatus.OK);
    }
    @PutMapping("/amend/{additionalHours}")
    public ResponseEntity<CarParkSlot> amendSlot(@RequestBody CarParkSlot carParkSlot,@PathVariable int additionalHours) {
        CarParkSlot amendedSlot = carParkService.amendSlot(carParkSlot.getId(), additionalHours);
        return new ResponseEntity<>(amendedSlot, HttpStatus.OK);
    }

    @DeleteMapping("/deallocate/{slotId}")
    public ResponseEntity<String> deallocateSlot(@PathVariable int slotId) {
        String response=carParkService.deallocateSlot(slotId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
