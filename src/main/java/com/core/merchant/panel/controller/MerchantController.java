package com.core.merchant.panel.controller;

import com.core.merchant.panel.entity.Merchant;
import com.core.merchant.panel.model.MerchantCreateRequest;
import com.core.merchant.panel.model.MerchantUpdateRequest;
import com.core.merchant.panel.service.MerchantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchants")
@AllArgsConstructor
@Slf4j
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping
    public ResponseEntity<Merchant> createMerchant(@Valid @RequestBody MerchantCreateRequest merchantCreateRequest) {
        log.info("Creating merchant: {}", merchantCreateRequest);
        Merchant merchant = merchantService.createMerchant(merchantCreateRequest);
        log.info("Merchant created: {}", merchant);
        return ResponseEntity.status(HttpStatus.CREATED).body(merchant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(
            @PathVariable Long id, @Valid @RequestBody MerchantUpdateRequest merchantUpdateRequest) {
        log.info("Updating merchant with id: {}", id);
        Merchant updatedMerchant = merchantService.updateMerchant(id, merchantUpdateRequest);
        log.info("Merchant updated: {}", updatedMerchant);
        return ResponseEntity.ok(updatedMerchant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Long id) {
        log.info("Fetching merchant with id: {}", id);
        Merchant merchant = merchantService.getMerchantById(id);
        return ResponseEntity.ok(merchant);
    }

    @GetMapping
    public ResponseEntity<Page<Merchant>> getAllMerchants(@PageableDefault(sort = "id") Pageable pageable) {
        log.info("Fetching all merchants");
        Page<Merchant> page = merchantService.getAllMerchants(pageable);
        return ResponseEntity.ok(page);
    }
}
