package com.test.pos.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO extends BasePhone {

    @JsonProperty("technology")
    private String technology;

    @JsonProperty("Bands")
    private String bands;

    @JsonProperty("bookedBy")
    private String bookedBy = "N/A";

    @JsonProperty("bookedOn")
    private LocalDateTime bookedDate;

    @JsonProperty("isAvailable")
    private String available;
}
