package com.core.merchant.panel.service;

import com.core.merchant.panel.entity.Gateway;
import com.core.merchant.panel.model.GatewayCreateRequest;
import com.core.merchant.panel.model.GatewayUpdateRequest;
import com.core.merchant.panel.model.dto.GatewayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GatewayService {
    Page<GatewayResponse> getAllGateways(Pageable pageable);
    Gateway getGatewayById(Long id);
    Gateway createGateway(GatewayCreateRequest request);
    Gateway updateGateway(Long id, GatewayUpdateRequest request);
    void deleteGateway(Long id);
}
