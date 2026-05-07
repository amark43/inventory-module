package com.example.inventory.controller;

import com.example.inventory.repository.DealerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DealerRepository dealerRepository;

    @GetMapping("/dealers/countBySubscription")
    @PreAuthorize("hasRole('GLOBAL_ADMIN')")
    public Map<String, Long> countBySubscription() {

        Map<String, Long> response = new HashMap<>();

        dealerRepository.countBySubscription()
                .forEach(row -> {
                    response.put(
                            row[0].toString(),
                            (Long) row[1]
                    );
                });

        return response;
    }
}