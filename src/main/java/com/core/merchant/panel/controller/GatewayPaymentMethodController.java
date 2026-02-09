package com.core.merchant.panel.controller;

import com.core.merchant.panel.entity.GatewayPaymentMethod;
import com.core.merchant.panel.model.GatewayPaymentMethodCreateRequest;
import com.core.merchant.panel.model.GatewayPaymentMethodUpdateRequest;
import com.core.merchant.panel.model.dto.GatewayPaymentMethodResponse;
import com.core.merchant.panel.service.GatewayPaymentMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gateway-payment-methods")
public class GatewayPaymentMethodController {

    private final GatewayPaymentMethodService gatewayPaymentMethodService;

    @PostMapping
    public ResponseEntity<GatewayPaymentMethodResponse> createGatewayPaymentMethod(
            @Valid @RequestBody GatewayPaymentMethodCreateRequest request) {
        log.info("Received request to create gateway payment method");
        GatewayPaymentMethodResponse response = gatewayPaymentMethodService.createGatewayPaymentMethod(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GatewayPaymentMethodResponse> updateGatewayPaymentMethod(
            @PathVariable Long id,
            @Valid @RequestBody GatewayPaymentMethodUpdateRequest request) {
        log.info("Received request to update gateway payment method with id: {}", id);
        GatewayPaymentMethodResponse response = gatewayPaymentMethodService.updateGatewayPaymentMethod(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GatewayPaymentMethodResponse> getGatewayPaymentMethodById(@PathVariable Long id) {
        log.info("Received request to get gateway payment method with id: {}", id);
        return ResponseEntity.ok(gatewayPaymentMethodService.getGatewayPaymentMethodById(id));
    }

    @GetMapping
    public ResponseEntity<Page<GatewayPaymentMethodResponse>> getAllGatewayPaymentMethods(
            @PageableDefault(size = 20) Pageable pageable) {
        log.info("Received request to get all gateway payment methods");
        return ResponseEntity.ok(gatewayPaymentMethodService.getAllGatewayPaymentMethods(pageable));
    }

    @GetMapping("/all/{gatewayId}")
    public ResponseEntity<Page<GatewayPaymentMethodResponse>> getGatewayPaymentMethodsByGatewayId(
            @PathVariable Long gatewayId,
            @PageableDefault(size = 20) Pageable pageable) {
        log.info("Received request to get payment methods for gateway with id: {}", gatewayId);
        return ResponseEntity.ok(gatewayPaymentMethodService.getGatewayPaymentMethodsByGatewayId(gatewayId, pageable));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGatewayPaymentMethod(@PathVariable Long id) {
        log.info("Received request to delete gateway payment method with id: {}", id);
        gatewayPaymentMethodService.deleteGatewayPaymentMethod(id);
    }
}
