package com.core.merchant.panel.model.dto;

public record GatewayMetadataResponse(
        Long id,
        String key,
        String value
) {}