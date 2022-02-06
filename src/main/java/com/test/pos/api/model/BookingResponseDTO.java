package com.test.pos.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookingResponseDTO {
    @JsonProperty("phoneId")
    private String id;

    @JsonProperty("phoneName")
    private String name;

    @JsonProperty("bookedBy")
    private String bookedBy;

    @JsonProperty("bookedOn")
    private LocalDateTime bookedDate;

    @JsonProperty("isAvailable")
    private String available;
}
