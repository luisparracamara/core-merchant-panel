package com.core.merchant.panel.model.dto;

import com.core.merchant.panel.entity.PaymentMethod;
import lombok.Builder;

@Builder
public record GatewayPaymentMethodResponse(
        Long id,
        PaymentMethod paymentMethod,
        String currency
) {}
