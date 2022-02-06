package com.test.pos.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.pos.api.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/api/v1/")
public interface PhoneOrderControllerAPI {

    @RequestMapping(value = "/bookPhone", method = RequestMethod.POST)
    ResponseEntity<BookingResponseDTO> bookPhone(@RequestBody BookingRequestDTO bookingRequest) throws JsonProcessingException;

    @RequestMapping(value = "/returnPhone", method = RequestMethod.DELETE)
    ResponseEntity<ReturnResponseDTO> returnPhone(@RequestParam("name") String phoneName) throws JsonProcessingException;

    @RequestMapping(value = "/phones", method = RequestMethod.GET)
    ResponseEntity<List<BookingResponseDTO>> getAllPhones();

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    ResponseEntity<PhoneDTO> getPhoneByName(@RequestParam("name") String phoneName) throws JsonProcessingException;


    @RequestMapping(value = "/health", method = RequestMethod.GET)
    ResponseEntity<String> checkHealth();

}
