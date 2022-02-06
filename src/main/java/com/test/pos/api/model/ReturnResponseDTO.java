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
public class ReturnResponseDTO {
    @JsonProperty("phoneId")
    String id;

    @JsonProperty("phoneName")
    String name;

    @JsonProperty("returnedBy")
    String returnedBy;

    @JsonProperty("returnedOn")
    LocalDateTime returnedOn;

    @JsonProperty("isAvailable")
    String available;
}
