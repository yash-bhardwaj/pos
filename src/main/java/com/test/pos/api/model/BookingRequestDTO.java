package com.test.pos.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingRequestDTO {
    @JsonProperty("phoneName")
    private String name;

    @JsonProperty("bookedBy")
    private String bookedBy;

}
