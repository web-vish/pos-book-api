package com.vish.posBookApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionResponseDTO {
    @JsonProperty("Positions")
    private List<PositionDTO> positions;
}
