package com.example.inventory.service;

import com.example.inventory.domain.Dealer;
import com.example.inventory.repository.DealerRepository;
import com.example.inventory.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealerService {

    private final DealerRepository dealerRepository;

    public Dealer create(Dealer dealer) {
        dealer.setTenantId(TenantContext.getTenantId());
        return dealerRepository.save(dealer);
    }

    public Dealer get(UUID id) {
        return dealerRepository
                .findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.FORBIDDEN,
                                "Cross tenant access denied"));
    }

    public Page<Dealer> getAll(Pageable pageable) {
        return dealerRepository.findAllByTenantId(
                TenantContext.getTenantId(),
                pageable
        );
    }

    public Dealer update(UUID id, Dealer request) {

        Dealer dealer = get(id);

        dealer.setName(request.getName());
        dealer.setEmail(request.getEmail());
        dealer.setSubscriptionType(request.getSubscriptionType());

        return dealerRepository.save(dealer);
    }

    public void delete(UUID id) {
        dealerRepository.delete(get(id));
    }
}