package com.core.merchant.panel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayPaymentMethodUpdateRequest {
    private Long paymentMethodId;
    private String currency;
}
