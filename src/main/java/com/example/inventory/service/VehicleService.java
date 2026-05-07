package com.example.inventory.service;

import com.example.inventory.domain.Dealer;
import com.example.inventory.domain.Status;
import com.example.inventory.domain.Vehicle;
import com.example.inventory.repository.DealerRepository;
import com.example.inventory.repository.VehicleRepository;
import com.example.inventory.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DealerRepository dealerRepository;

    public Vehicle create(Vehicle vehicle) {
        vehicle.setTenantId(TenantContext.getTenantId());
        return vehicleRepository.save(vehicle);
    }

    public Vehicle get(UUID id) {
        return vehicleRepository
                .findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.FORBIDDEN,
                                "Cross tenant access denied"));
    }

    public Page<Vehicle> getAll(String model,
                                Status status,
                                BigDecimal priceMin,
                                BigDecimal priceMax,
                                String subscription,
                                Pageable pageable) {

        Page<Vehicle> page = vehicleRepository.findAllByTenantId(
                TenantContext.getTenantId(),
                pageable
        );

        List<Vehicle> filtered = page.getContent().stream()
                .filter(vehicle -> {

                    if (model != null &&
                            !vehicle.getModel().toLowerCase()
                                    .contains(model.toLowerCase())) {
                        return false;
                    }

                    if (status != null && vehicle.getStatus() != status) {
                        return false;
                    }

                    if (priceMin != null &&
                            vehicle.getPrice().compareTo(priceMin) < 0) {
                        return false;
                    }

                    if (priceMax != null &&
                            vehicle.getPrice().compareTo(priceMax) > 0) {
                        return false;
                    }

                    if (subscription != null) {

                        Dealer dealer = dealerRepository.findById(
                                vehicle.getDealerId()
                        ).orElse(null);

                        if (dealer == null ||
                                !dealer.getSubscriptionType().name()
                                        .equalsIgnoreCase(subscription)) {
                            return false;
                        }
                    }

                    return true;
                })
                .toList();

        return new org.springframework.data.domain.PageImpl<>(
                filtered,
                pageable,
                filtered.size()
        );
    }

    public Vehicle update(UUID id, Vehicle request) {

        Vehicle vehicle = get(id);

        vehicle.setModel(request.getModel());
        vehicle.setPrice(request.getPrice());
        vehicle.setStatus(request.getStatus());

        return vehicleRepository.save(vehicle);
    }

    public void delete(UUID id) {
        vehicleRepository.delete(get(id));
    }


}