package com.core.merchant.panel.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethodUpdateRequest {
    private String code;
    private String logo;
    private String paymentType;
    private String country;
    private String description;

    @Pattern(regexp = "ENABLED|DISABLED", message = "Status must be either ENABLED or DISABLED")
    private String status;
    
    private String name;
    private String provider;
}
