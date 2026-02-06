package com.core.merchant.panel.controller;

import com.core.merchant.panel.entity.PaymentMethod;
import com.core.merchant.panel.model.PaymentMethodCreateRequest;
import com.core.merchant.panel.model.PaymentMethodUpdateRequest;
import com.core.merchant.panel.service.PaymentMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment-methods")
@RequiredArgsConstructor
@Slf4j
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<PaymentMethod> createPaymentMethod(
            @Valid @RequestBody PaymentMethodCreateRequest request) {
        log.info("Creating new payment method: {}", request.getName());
        PaymentMethod paymentMethod = paymentMethodService.createPaymentMethod(request);
        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(
            @PathVariable Long id,
            @Valid @RequestBody PaymentMethodUpdateRequest request) {
        log.info("Updating payment method with id: {}", id);
        PaymentMethod paymentMethod = paymentMethodService.updatePaymentMethod(id, request);
        return ResponseEntity.ok(paymentMethod);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable Long id) {
        log.info("Fetching payment method with id: {}", id);
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(id);
        return ResponseEntity.ok(paymentMethod);
    }

    @GetMapping
    public ResponseEntity<Page<PaymentMethod>> getAllPaymentMethods(
            @PageableDefault(sort = "id") Pageable pageable) {
        log.info("Fetching all payment methods");
        Page<PaymentMethod> page = paymentMethodService.getAllPaymentMethods(pageable);
        return ResponseEntity.ok(page);
    }

}
