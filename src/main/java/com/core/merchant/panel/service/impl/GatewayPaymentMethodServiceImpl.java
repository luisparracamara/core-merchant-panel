package com.core.merchant.panel.service.impl;

import com.core.merchant.panel.entity.Gateway;
import com.core.merchant.panel.entity.GatewayPaymentMethod;
import com.core.merchant.panel.entity.PaymentMethod;
import com.core.merchant.panel.exception.NotFoundException;
import com.core.merchant.panel.mapper.PanelMapperInterface;
import com.core.merchant.panel.model.GatewayPaymentMethodCreateRequest;
import com.core.merchant.panel.model.GatewayPaymentMethodUpdateRequest;
import com.core.merchant.panel.model.dto.GatewayPaymentMethodResponse;
import com.core.merchant.panel.repository.GatewayPaymentMethodRepository;
import com.core.merchant.panel.repository.GatewayRepository;
import com.core.merchant.panel.repository.PaymentMethodRepository;
import com.core.merchant.panel.service.GatewayPaymentMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GatewayPaymentMethodServiceImpl implements GatewayPaymentMethodService {

    private final GatewayPaymentMethodRepository gatewayPaymentMethodRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final GatewayRepository gatewayRepository;
    private final PanelMapperInterface mapper;

    @Override
    public Page<GatewayPaymentMethodResponse> getAllGatewayPaymentMethods(Pageable pageable) {
        log.info("Fetching all gateway payment methods with pagination");
        return gatewayPaymentMethodRepository.findAll(pageable).map(gatewayPaymentMethod -> new GatewayPaymentMethodResponse(
                gatewayPaymentMethod.getId(),
                gatewayPaymentMethod.getPaymentMethod(),
                gatewayPaymentMethod.getCurrency()
        ));
    }

    @Override
    public GatewayPaymentMethodResponse getGatewayPaymentMethodById(Long id) {
        log.info("Fetching gateway payment method with id: {}", id);
        GatewayPaymentMethod gatewayPaymentMethod = findGatewayPaymentMethodById(id);
        return mapper.toGatewayPaymentMethodResponse(gatewayPaymentMethod);
    }

    @Override
    @Transactional
    public GatewayPaymentMethodResponse createGatewayPaymentMethod(GatewayPaymentMethodCreateRequest request) {
        log.info("Creating new gateway payment method");
        
        PaymentMethod paymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId())
                .orElseThrow(() -> new NotFoundException("Payment method not found with id: " + request.getPaymentMethodId()));
        
        Gateway gateway = gatewayRepository.findById(request.getGatewayId())
                .orElseThrow(() -> new NotFoundException("Gateway not found with id: " + request.getGatewayId()));

        GatewayPaymentMethod gatewayPaymentMethod = GatewayPaymentMethod.builder()
                .paymentMethod(paymentMethod)
                .gateway(gateway)
                .currency(request.getCurrency())
                .build();
                
        GatewayPaymentMethod savedGPM = gatewayPaymentMethodRepository.save(gatewayPaymentMethod);
        log.info("Created gateway payment method with id: {}", savedGPM.getId());
        return mapper.toGatewayPaymentMethodResponse(savedGPM);
    }

    @Override
    @Transactional
    public GatewayPaymentMethodResponse updateGatewayPaymentMethod(Long id, GatewayPaymentMethodUpdateRequest request) {
        log.info("Updating gateway payment method with id: {}", id);
        GatewayPaymentMethod existingGPM = findGatewayPaymentMethodById(id);

        if (request.getPaymentMethodId() != null) {
            PaymentMethod newPm = paymentMethodRepository.findById(request.getPaymentMethodId())
                    .orElseThrow(() -> new NotFoundException("Payment Method not found"));
            existingGPM.setPaymentMethod(newPm);
        }
        
        mapper.updateGatewayPaymentMethodFromRequest(request, existingGPM);
        GatewayPaymentMethod updatedGPM = gatewayPaymentMethodRepository.save(existingGPM);
        
        log.info("Updated gateway payment method with id: {}", id);
        return mapper.toGatewayPaymentMethodResponse(updatedGPM);
    }

    @Override
    @Transactional
    public void deleteGatewayPaymentMethod(Long id) {
        log.info("Deleting gateway payment method with id: {}", id);
        GatewayPaymentMethod gatewayPaymentMethod = findGatewayPaymentMethodById(id);
        gatewayPaymentMethodRepository.delete(gatewayPaymentMethod);
        log.info("Deleted gateway payment method with id: {}", id);
    }

    @Override
    public Page<GatewayPaymentMethodResponse> getGatewayPaymentMethodsByGatewayId(Long gatewayId, Pageable pageable) {
        if (!gatewayRepository.existsById(gatewayId)) {
            throw new NotFoundException("Gateway not found with id: " + gatewayId);
        }
        return gatewayPaymentMethodRepository.findByGatewayId(gatewayId, pageable)
                .map(mapper::toGatewayPaymentMethodResponse);
    }

    private GatewayPaymentMethod findGatewayPaymentMethodById(Long id) {
        return gatewayPaymentMethodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gateway payment method not found with id: " + id));
    }
}
