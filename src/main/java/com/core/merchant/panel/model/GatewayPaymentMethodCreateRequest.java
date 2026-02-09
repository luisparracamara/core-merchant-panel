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
public class GatewayPaymentMethodCreateRequest {
    @NotNull(message = "Payment method ID is required")
    private Long paymentMethodId;

    @NotNull(message = "Gateway ID is required")
    private Long gatewayId;

    @NotBlank(message = "Currency is required")
    private String currency;
}
