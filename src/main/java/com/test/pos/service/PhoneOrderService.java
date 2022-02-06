package com.test.pos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.pos.api.model.*;

import java.util.List;

public interface PhoneOrderService {
    BookingResponseDTO saveBookedDevice(BookingRequestDTO bookingRequest) throws JsonProcessingException;

    List<BookingResponseDTO> getAll();

    PhoneDTO getPhoneByName(String phoneName) throws JsonProcessingException;

    ReturnResponseDTO executePhoneReturn(String phoneName);
}
