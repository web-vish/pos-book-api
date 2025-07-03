package com.vish.posBookApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Positions", uniqueConstraints = @UniqueConstraint(columnNames = {"account", "security"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Positions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String account;
    private String security;
    private Integer quantity;
}
