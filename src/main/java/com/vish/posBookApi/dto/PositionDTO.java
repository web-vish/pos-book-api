package com.vish.posBookApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO {
    @JsonProperty("ID")
    private Long id;
    @JsonProperty("Account")
    private String account;
    @JsonProperty("Security")
    private String security;
    @JsonProperty("Quantity")
    private int quantity;
    @JsonProperty("Events")
    private List<EventDTO> events;

}

