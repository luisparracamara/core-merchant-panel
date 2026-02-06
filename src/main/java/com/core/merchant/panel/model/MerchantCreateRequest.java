package com.core.merchant.panel.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantCreateRequest {

    @NotBlank(message = "Merchant name is required")
    private String name;

    @NotBlank
    @Size(min = 2, max = 2, message = "Country has to be 2 letters (ISO 3166)")
    private String country;

    private String businessUnit;

    @Pattern(regexp = "ENABLED|DISABLED", message = "Status must be either ENABLED or DISABLED")
    private String status;

    private String logo;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    private String loginId;
    private String secretKey;

}
