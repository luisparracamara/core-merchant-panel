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

    @NotBlank(message = "Merchant name needed")
    private String name;

    @NotBlank
    @Size(min = 2, max = 2, message = "Country has to be 2 letters (ISO 3166)")
    private String country;

    private String businessUnit;

    @Pattern(regexp = "ACTIVE|INACTIVE", message = "Invalid status")
    private String status;

    private String logo;

    @NotBlank(message = "Email needed")
    @Email
    private String email;

    private String loginId;
    private String secretKey;

}
