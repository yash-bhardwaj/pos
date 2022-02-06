package com.test.pos.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class BasePhone {
    @JsonProperty("phoneId")
    public String id;

    @JsonProperty("phoneName")
    String name;
}
