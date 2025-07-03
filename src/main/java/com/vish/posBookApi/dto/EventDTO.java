package com.vish.posBookApi.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
        @JsonProperty("ID")
        private Long id;
        @JsonProperty("Action")
        private String action;
        @JsonProperty("Account")
        private String account;
        @JsonProperty("Security")
        private String security;
        @JsonProperty("Quantity")
        private Integer quantity;
        @JsonProperty("EventID")
        private Long eventId;

        public EventDTO(Long id, String action, String account, String security, Integer quantity) {
            this.id = id;
            this.action = action;
            this.account = account;
            this.security = security;
            this.quantity = quantity;
        }
}
