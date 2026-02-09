package com.core.merchant.panel.service.impl;

import com.core.merchant.panel.entity.Gateway;
import com.core.merchant.panel.exception.NotFoundException;
import com.core.merchant.panel.mapper.PanelMapperInterface;
import com.core.merchant.panel.model.GatewayCreateRequest;
import com.core.merchant.panel.model.GatewayUpdateRequest;
import com.core.merchant.panel.model.dto.GatewayMetadataResponse;
import com.core.merchant.panel.model.dto.GatewayResponse;
import com.core.merchant.panel.repository.GatewayRepository;
import com.core.merchant.panel.service.GatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GatewayServiceImpl implements GatewayService {

    private final GatewayRepository gatewayRepository;
    private final PanelMapperInterface mapper;

    @Override
    public Page<GatewayResponse> getAllGateways(Pageable pageable) {
        log.info("Fetching all gateways with pagination");
        return gatewayRepository.findAll(pageable)
                .map(g -> new GatewayResponse(
                        g.getId(),
                        g.getName(),
                        g.getConnectorName(),
                        g.getMerchantId(),
                        g.getMetadata().stream()
                                .map(m -> new GatewayMetadataResponse(
                                        m.getId(),
                                        m.getMetaKey(),
                                        m.getMetaValue()
                                ))
                                .toList()
                ));
    }

    @Override
    public Gateway getGatewayById(Long id) {
        log.info("Fetching gateway with id: {}", id);
        return findGatewayById(id);
    }

    @Override
    public Gateway createGateway(GatewayCreateRequest request) {
        log.info("Creating new gateway: {}", request.getName());
        Gateway gateway = Gateway.builder()
                .name(request.getName())
                .connectorName(request.getConnectorName())
                .merchantId(request.getMerchantId())
                .build();
        Gateway savedGateway = gatewayRepository.save(gateway);
        log.info("Gateway created with id: {}", savedGateway.getId());
        return gateway;
    }

    @Override
    public Gateway updateGateway(Long id, GatewayUpdateRequest request) {
        log.info("Updating gateway with id: {}", id);
        Gateway existingGateway = findGatewayById(id);
        mapper.updateGatewayFromRequest(request, existingGateway);
        Gateway updatedGateway = gatewayRepository.save(existingGateway);
        log.info("Gateway with id: {} updated", id);
        return updatedGateway;
    }

    @Override
    public void deleteGateway(Long id) {
        log.info("Deleting gateway with id: {}", id);
        Gateway gateway = findGatewayById(id);
        gatewayRepository.delete(gateway);
        log.info("Gateway with id: {} deleted", id);
    }

    private Gateway findGatewayById(Long id) {
        return gatewayRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gateway not found with id: " + id));
    }
}
