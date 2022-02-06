package com.test.pos.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.pos.api.model.*;
import com.test.pos.dao.PhoneOrderRepository;
import com.test.pos.dao.entity.Phone;
import com.test.pos.service.PhoneOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PhoneOrderServiceImpl implements PhoneOrderService {

    private static final String SAMSUNG_GALAXY_S9 = "60d437898f19b751ae3538b8";
    private static final String SAMSUNG_GALAXY_S8 = "60d438f38f19b751ae353e62";
    private static final String SAMSUNG_GALAXY_S8_PLUS = "60d438e88f19b751ae353e36";
    private static final String GOOGLE_NEXUS_6 = "60d43cc78f19b751ae354d8b"; // couldn't find Motorola Nexus 6 on used Client
    private static final String ONE_PLUS_9 = "60d4422a8f19b751ae356250";
    private static final String IPHONE_13 = "6181c55c58808868a066d1c4";
    private static final String IPHONE_12 = "60d4333c8f19b751ae352716";
    private static final String IPHONE_11 = "60d434b58f19b751ae352d29";
    private static final String IPHONE_X = "60d4384e8f19b751ae353bce";
    private static final String NOKIA_LUMIA_2520 = "60d443d18f19b751ae356887"; // couldn't find Nokia 3310 on used Client

    @Autowired
    private PhoneOrderRepository repository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public BookingResponseDTO saveBookedDevice(BookingRequestDTO bookingRequest) throws JsonProcessingException {
        Phone phone = repository.findByName(bookingRequest.getName());

        JsonNode data = getPhoneData(bookingRequest.getName());
        String bands = data.get("Cellular").get("SIM Mobile Data").asText();
        String tech = data.get("Processor").get("CPU").asText();

        if(null != phone && phone.getAvailable()) {
            Phone savedPhone = repository.save(new Phone(phone.getId(), bookingRequest.getName(), LocalDateTime.now(), bookingRequest.getBookedBy(), false, tech, bands));

            return new BookingResponseDTO(savedPhone.getId(), savedPhone.getName(), savedPhone.getBookedBy(), savedPhone.getBookedDate(), savedPhone.getAvailable() ? "Yes" : "No");
        } else {
            if( null == phone) {
                String phoneId =  UUID.randomUUID().toString();
                Phone savedPhone = repository.save(new Phone(phoneId, bookingRequest.getName(), LocalDateTime.now(), bookingRequest.getBookedBy(), false, tech, bands));
                return new BookingResponseDTO(savedPhone.getId(), savedPhone.getName(), savedPhone.getBookedBy(), savedPhone.getBookedDate(), savedPhone.getAvailable() ? "Yes" : "No");
            }
            throw new RuntimeException(bookingRequest.getName()+ " can't be booked again!");
        }
    }

    @Override
    public List<BookingResponseDTO> getAll() {
        return repository.findAll()
                .parallelStream()
                .map(phone -> new BookingResponseDTO(phone.getId(), phone.getName(), phone.getBookedBy(), phone.getBookedDate(), phone.getAvailable() ? "Yes" : "No"))
                .collect(Collectors.toList());
    }

    @Override
    public PhoneDTO getPhoneByName(String phoneName) throws JsonProcessingException {
        Phone phone = repository.findByName(phoneName);
        JsonNode data = getPhoneData(phoneName);
        String bands = data.get("Cellular").get("SIM Mobile Data").asText();
        String tech = data.get("Processor").get("CPU").asText();

        PhoneDTO phoneResponse = new PhoneDTO();

        if (null == phone) {
            phoneResponse.setBands(bands);
            phoneResponse.setTechnology(tech);
            phone = repository.save(new Phone(UUID.randomUUID().toString(), phoneName, LocalDateTime.now(), "N/A", true, tech, bands));
        } else {
            phoneResponse.setTechnology(phone.getTechnology());
            phoneResponse.setBands(phone.getBands());
        }
        phoneResponse.setId(phone.getId());
        phoneResponse.setName(phone.getName());
        phoneResponse.setAvailable(phone.getAvailable() ? "Yes" : "No");
        phoneResponse.setBookedBy(phone.getAvailable() ? "N/A" : phone.getBookedBy());
        phoneResponse.setBookedDate(phone.getBookedDate());

        return phoneResponse;
    }

    @Override
    public ReturnResponseDTO executePhoneReturn(String phoneName) {
        Phone phone = repository.findByName(phoneName);

        if (null == phone) throw new RuntimeException(phoneName+ " was never booked, so it can't be returned!");
        else if (phone.getAvailable()) throw new RuntimeException(phoneName+ " was never booked, so it can't be returned!");
        else {
            phone.setAvailable(true);
            repository.save(phone);
        }
        return new ReturnResponseDTO(phone.getId(), phone.getName(), phone.getBookedBy(), LocalDateTime.now(), phone.getAvailable() ? "Yes" : "No");
    }

    private JsonNode getPhoneData(String phoneName) throws JsonProcessingException {
        String endPoint;
        switch (phoneName) {
            case "Samsung Galaxy S9":
                endPoint = SAMSUNG_GALAXY_S9;
                break;
            case "Samsung Galaxy S8":
                endPoint = SAMSUNG_GALAXY_S8;
                break;
            case "Samsung Galaxy S8+":
                endPoint = SAMSUNG_GALAXY_S8_PLUS;
                break;
            case "Google Nexus 6":
                endPoint = GOOGLE_NEXUS_6;
                break;
            case "Oneplus 9":
                endPoint = ONE_PLUS_9;
                break;
            case "Apple iPhone 13":
                endPoint = IPHONE_13;
                break;
            case "Apple iPhone 12":
                endPoint = IPHONE_12;
                break;
            case "Apple iPhone 11":
                endPoint = IPHONE_11;
                break;
            case "iPhone X":
                endPoint = IPHONE_X;
                break;
            case "Nokia Lumia 2520":
                endPoint = NOKIA_LUMIA_2520;
                break;
            default:
                throw new RuntimeException("The Phone doesn't exist!");
        }

        String response = restTemplate.getForObject("/" + endPoint, String.class);
        return new ObjectMapper().readTree(response).get("data").get("product").get(0).get("Inside");
    }
}
