package com.example.inventory.controller;

import com.example.inventory.domain.Dealer;
import com.example.inventory.service.DealerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dealers")
@RequiredArgsConstructor
public class DealerController {

    private final DealerService dealerService;

    @PostMapping
    public Dealer create(@RequestBody Dealer dealer) {
        return dealerService.create(dealer);
    }

    @GetMapping("/{id}")
    public Dealer get(@PathVariable UUID id) {
        return dealerService.get(id);
    }

    @GetMapping
    public Page<Dealer> getAll(Pageable pageable) {
        return dealerService.getAll(pageable);
    }

    @PatchMapping("/{id}")
    public Dealer update(@PathVariable UUID id,
                         @RequestBody Dealer dealer) {
        return dealerService.update(id, dealer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        dealerService.delete(id);
    }
}