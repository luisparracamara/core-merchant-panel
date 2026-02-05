package com.core.merchant.panel.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantUpdateRequest {

    private String name;

    @Size(min = 2, max = 2, message = "Country has to be 2 letters (ISO 3166)")
    private String country;

    private String businessUnit;

    @Pattern(regexp = "ACTIVE|INACTIVE", message = "Invalid status")
    private String status;

    private String logo;

    @Email
    private String email;

    private String loginId;
    private String secretKey;

}
