package com.core.merchant.panel.service;

import com.core.merchant.panel.entity.Merchant;
import com.core.merchant.panel.model.MerchantCreateRequest;
import com.core.merchant.panel.model.MerchantUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MerchantService {

    Merchant createMerchant(MerchantCreateRequest merchantCreateRequest);
    
    Merchant updateMerchant(Long id, MerchantUpdateRequest merchantUpdateRequest);
    
    Merchant getMerchantById(Long id);

    Page<Merchant> getAllMerchants(Pageable pageable);
}
