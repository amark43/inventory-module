package com.example.inventory.controller;

import com.example.inventory.domain.Status;
import com.example.inventory.domain.Vehicle;
import com.example.inventory.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicleService.create(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle get(@PathVariable UUID id) {
        return vehicleService.get(id);
    }

    @GetMapping
    public Page<Vehicle> getAll(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax,
            @RequestParam(required = false) String subscription,
            Pageable pageable
    ) {

        return vehicleService.getAll(
                model,
                status,
                priceMin,
                priceMax,
                subscription,
                pageable
        );
    }

    @PatchMapping("/{id}")
    public Vehicle update(@PathVariable UUID id,
                          @RequestBody Vehicle vehicle) {
        return vehicleService.update(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        vehicleService.delete(id);
    }
}