package com.example.inventory.repository;

import com.example.inventory.domain.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DealerRepository extends JpaRepository<Dealer, UUID> {

    Optional<Dealer> findByIdAndTenantId(UUID id, String tenantId);

    Page<Dealer> findAllByTenantId(String tenantId, Pageable pageable);

    @Query("SELECT d.subscriptionType, COUNT(d) FROM Dealer d GROUP BY d.subscriptionType")
    List<Object[]> countBySubscription();
}