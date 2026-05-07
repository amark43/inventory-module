package com.example.inventory.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue
    private UUID id;

    private String tenantId;

    private UUID dealerId;

    private String model;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;
}