package com.example.inventory.repository;

import com.example.inventory.domain.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    Optional<Vehicle> findByIdAndTenantId(UUID id, String tenantId);

    Page<Vehicle> findAllByTenantId(String tenantId, Pageable pageable);
}