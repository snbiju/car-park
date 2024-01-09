package com.personal.carpark.controller;

import com.personal.carpark.domain.CarParkSlot;
import com.personal.carpark.exception.SlotNotFoundException;
import com.personal.carpark.service.CarParkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CarParkControllerTest {

    @InjectMocks
    private CarParkController carParkController;

    @Mock
    private CarParkService carParkService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllocateSlot() throws SlotNotFoundException {
        CarParkSlot requestSlot = new CarParkSlot();
        requestSlot.setRegistrationNumber("ABC123");
        when(carParkService.allocateSlot("ABC123")).thenReturn(requestSlot);

        ResponseEntity<CarParkSlot> responseEntity = carParkController.allocateSlot(requestSlot);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(requestSlot, responseEntity.getBody());
    }

    @Test
    public void testGetSlots() {
        List<Integer> availableSlots = Arrays.asList(1, 2, 3);
        when(carParkService.availableSlots()).thenReturn(availableSlots);
        ResponseEntity<List<Integer>> responseEntity = carParkController.getSlots();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(availableSlots, responseEntity.getBody());
    }

    @Test
    public void testAmendSlot() {
        CarParkSlot requestSlot = new CarParkSlot();
        requestSlot.setId(1);
        when(carParkService.amendSlot(1, 2)).thenReturn(requestSlot);

        ResponseEntity<CarParkSlot> responseEntity = carParkController.amendSlot(requestSlot, 2);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(requestSlot, responseEntity.getBody());
    }

    @Test
    public void testDeallocateSlot() {
        when(carParkService.deallocateSlot(1)).thenReturn("Slot deallocated successfully");
        ResponseEntity<String> responseEntity = carParkController.deallocateSlot(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Slot deallocated successfully", responseEntity.getBody());
    }
}
