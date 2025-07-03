package com.vish.posBookApi.model;

import com.vish.posBookApi.dto.EventDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String action;
    private String account;
    private String security;
    @Column
    private Integer quantity;

    public static Events from(EventDTO e) {
        return new Events(e.getId(), e.getAction(), e.getAccount(), e.getSecurity(), e.getQuantity());
    }
}
