package com.core.merchant.panel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayCreateRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Connector name is required")
    private String connectorName;

    @NotNull(message = "Merchant is required")
    private Long merchantId;

}
