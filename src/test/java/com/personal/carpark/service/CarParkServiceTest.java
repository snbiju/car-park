package com.personal.carpark.service;

import com.personal.carpark.domain.CarParkSlot;
import com.personal.carpark.exception.SlotModificationNotAllowedException;
import com.personal.carpark.exception.SlotNotFoundException;
import com.personal.carpark.repository.CarParkSlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class CarParkServiceTest {

    @InjectMocks
    private CarParkService carParkService;

    @Mock
    private CarParkSlotRepository carParkSlotRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllocateSlot() throws SlotNotFoundException {
        String registrationNumber = "ABC123";

        CarParkSlot savedSlot = new CarParkSlot();
        savedSlot.setId(1);
        savedSlot.setRegistrationNumber(registrationNumber);
        savedSlot.setEntryTime(LocalDateTime.now());
        savedSlot.setDuration(2);

        when(carParkSlotRepository.save(any(CarParkSlot.class))).thenReturn(savedSlot);

        CarParkSlot allocatedSlot = carParkService.allocateSlot(registrationNumber);

        assertNotNull(allocatedSlot);
        assertEquals(savedSlot, allocatedSlot);
    }

    @Test
    public void testAmendSlot() throws SlotNotFoundException, SlotModificationNotAllowedException {
        int slotId =2;
        int additionalHours = 2;

        CarParkSlot existingSlot = new CarParkSlot();
        existingSlot.setId(slotId);
        existingSlot.setEntryTime(LocalDateTime.now().minusHours(1));
        existingSlot.setDuration(2);

        when(carParkSlotRepository.findById(slotId)).thenReturn(Optional.of(existingSlot).get());
        when(carParkSlotRepository.save(existingSlot)).thenReturn(Optional.of(existingSlot).get());

        CarParkSlot amendedSlot = carParkService.amendSlot(slotId, additionalHours);

        assertNotNull(amendedSlot);
        assertEquals(existingSlot, amendedSlot);
        assertEquals(existingSlot.getDuration() , amendedSlot.getDuration());
    }

    @Test
    public void testAmendSlotNotAllowed() throws SlotNotFoundException,SlotModificationNotAllowedException {
        int slotId = 2;
        int additionalHours = 2;

        CarParkSlot existingSlot = new CarParkSlot();
        existingSlot.setId(slotId);
        existingSlot.setEntryTime(LocalDateTime.now().minusHours(3));
        existingSlot.setDuration(2);

        when(carParkSlotRepository.findById(slotId)).thenReturn(Optional.of(existingSlot).get());
        assertThrows(SlotModificationNotAllowedException.class, () ->
                carParkService.amendSlot(2, additionalHours));
    }

    @Test
    public void testDeallocateSlot() throws SlotNotFoundException {
        int slotId = 1;

        CarParkSlot existingSlot = new CarParkSlot();
        existingSlot.setId(slotId);
        existingSlot.setDuration(3);

        when(carParkSlotRepository.findById(slotId)).thenReturn(Optional.of(existingSlot).get());

        String deallocationResponse = carParkService.deallocateSlot(slotId);

        assertNotNull(deallocationResponse);
        assertTrue(deallocationResponse.contains("Car deallocated from Slot:" + slotId));
        assertTrue(deallocationResponse.contains("Total Charge :30.0"));
    }

    @Test
    public void testAvailableSlots() {
        List<Integer> availableSlots = Collections.singletonList(1);

        when(carParkSlotRepository.getSlots()).thenReturn(availableSlots);

        List<Integer> result = carParkService.availableSlots();

        assertNotNull(result);
        assertEquals(availableSlots, result);
    }
}
