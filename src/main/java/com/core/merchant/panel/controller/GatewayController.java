package com.core.merchant.panel.controller;

import com.core.merchant.panel.entity.Gateway;
import com.core.merchant.panel.model.GatewayCreateRequest;
import com.core.merchant.panel.model.GatewayUpdateRequest;
import com.core.merchant.panel.model.dto.GatewayResponse;
import com.core.merchant.panel.service.GatewayService;
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
@RequestMapping("/api/v1/gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    @PostMapping
    public ResponseEntity<Gateway> createGateway(
            @Valid @RequestBody GatewayCreateRequest request) {
        log.info("Received request to create gateway: {}", request.getName());
        Gateway response = gatewayService.createGateway(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gateway> updateGateway(
            @PathVariable Long id,
            @Valid @RequestBody GatewayUpdateRequest request) {
        log.info("Received request to update gateway with id: {}", id);
        Gateway response = gatewayService.updateGateway(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gateway> getGatewayById(@PathVariable Long id) {
        log.info("Received request to get gateway with id: {}", id);
        return ResponseEntity.ok(gatewayService.getGatewayById(id));
    }

    @GetMapping
    public ResponseEntity<Page<GatewayResponse>> getAllGateways(@PageableDefault(sort = "id") Pageable pageable) {
        log.info("Received request to get all gateways");
        return ResponseEntity.ok(gatewayService.getAllGateways(pageable));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGateway(@PathVariable Long id) {
        log.info("Received request to delete gateway with id: {}", id);
        gatewayService.deleteGateway(id);
    }
}
