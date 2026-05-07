package com.example.inventory.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Dealer {

    @Id
    @GeneratedValue
    private UUID id;

    private String tenantId;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
}