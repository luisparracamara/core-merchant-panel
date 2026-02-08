package com.core.merchant.panel.model.dto;

import java.util.List;

public record GatewayResponse(
        Long id,
        String name,
        String connectorName,
        Long merchantId,
        List<GatewayMetadataResponse> metadata
) {}