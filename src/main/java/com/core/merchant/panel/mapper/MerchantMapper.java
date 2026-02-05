package com.core.merchant.panel.mapper;

import com.core.merchant.panel.entity.Merchant;
import com.core.merchant.panel.model.MerchantCreateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MerchantMapper {

    public Merchant toMerchant(MerchantCreateRequest merchantCreateRequest, String secretKey) {
        return Merchant.builder()
                .name(merchantCreateRequest.getName())
                .country(merchantCreateRequest.getCountry())
                .businessUnit(merchantCreateRequest.getBusinessUnit())
                .status(merchantCreateRequest.getStatus())
                .logo(merchantCreateRequest.getLogo())
                .email(merchantCreateRequest.getEmail())
                .creationDate(LocalDateTime.now())
                .loginId(UUID.randomUUID().toString())
                .secretKey(secretKey)
                .build();
    }

}
