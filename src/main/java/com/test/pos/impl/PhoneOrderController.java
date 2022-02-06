package com.test.pos.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.pos.api.model.*;
import com.test.pos.api.PhoneOrderControllerAPI;
import com.test.pos.service.PhoneOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class PhoneOrderController implements PhoneOrderControllerAPI {

    @Autowired
    private PhoneOrderService phoneOrderService;

    @Override
    public ResponseEntity<BookingResponseDTO> bookPhone(@RequestBody BookingRequestDTO bookingRequest) throws JsonProcessingException {

        return ResponseEntity.ok(phoneOrderService.saveBookedDevice(bookingRequest));
    }

    @Override
    public ResponseEntity<ReturnResponseDTO> returnPhone(String phoneName) {
        return ResponseEntity.ok(phoneOrderService.executePhoneReturn(phoneName));
    }

    @Override
    public ResponseEntity<List<BookingResponseDTO>> getAllPhones() {
        return ResponseEntity.ok(phoneOrderService.getAll());
    }

    @Override
    public ResponseEntity<PhoneDTO> getPhoneByName(String phoneName) throws JsonProcessingException {
        return ResponseEntity.ok(phoneOrderService.getPhoneByName(phoneName));
    }

    @Override
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("The Phone Order Service is running!");
    }
}
