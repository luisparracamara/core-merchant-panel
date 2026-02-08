package com.core.merchant.panel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayUpdateRequest {
    private String name;
    private String connectorName;
    private Long merchantId;
}
