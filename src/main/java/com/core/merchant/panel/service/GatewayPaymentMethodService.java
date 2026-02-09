package com.core.merchant.panel.service;

import com.core.merchant.panel.model.GatewayPaymentMethodCreateRequest;
import com.core.merchant.panel.model.GatewayPaymentMethodUpdateRequest;
import com.core.merchant.panel.model.dto.GatewayPaymentMethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GatewayPaymentMethodService {
    Page<GatewayPaymentMethodResponse> getAllGatewayPaymentMethods(Pageable pageable);
    Page<GatewayPaymentMethodResponse> getGatewayPaymentMethodsByGatewayId(Long gatewayId, Pageable pageable);
    GatewayPaymentMethodResponse getGatewayPaymentMethodById(Long id);
    GatewayPaymentMethodResponse createGatewayPaymentMethod(GatewayPaymentMethodCreateRequest request);
    GatewayPaymentMethodResponse updateGatewayPaymentMethod(Long id, GatewayPaymentMethodUpdateRequest request);
    void deleteGatewayPaymentMethod(Long id);
}
