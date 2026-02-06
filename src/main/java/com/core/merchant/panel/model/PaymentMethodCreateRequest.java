package com.core.merchant.panel.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethodCreateRequest {

    @NotBlank(message = "Code is required")
    private String code;

    private String logo;

    @NotBlank(message = "Payment type is required")
    private String paymentType;

    @NotBlank(message = "Country is required")
    private String country;

    private String description;

    @Pattern(regexp = "ENABLED|DISABLED", message = "Status must be either ENABLED or DISABLED")
    private String status;

    @NotBlank(message = "Name is required")
    private String name;

    private String provider;
}
