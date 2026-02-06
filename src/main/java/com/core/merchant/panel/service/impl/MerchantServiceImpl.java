package com.core.merchant.panel.service.impl;

import com.core.merchant.panel.entity.Merchant;
import com.core.merchant.panel.exception.NotFoundException;
import com.core.merchant.panel.mapper.PanelMapperInterface;
import com.core.merchant.panel.model.MerchantCreateRequest;
import com.core.merchant.panel.model.MerchantUpdateRequest;
import com.core.merchant.panel.repository.MerchantRepository;
import com.core.merchant.panel.service.MerchantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final PanelMapperInterface panelMapperInterface;

    @Override
    public Merchant createMerchant(MerchantCreateRequest merchantCreateRequest) {

        Merchant merchant = Merchant.builder()
                .name(merchantCreateRequest.getName())
                .country(merchantCreateRequest.getCountry().toUpperCase(Locale.ROOT))
                .businessUnit(merchantCreateRequest.getBusinessUnit())
                .status(merchantCreateRequest.getStatus())
                .logo(merchantCreateRequest.getLogo())
                .email(merchantCreateRequest.getEmail())
                .creationDate(LocalDateTime.now())
                .loginId(UUID.randomUUID().toString())
                .secretKey(generateSecretKey())
                .build();

        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant updateMerchant(Long id, MerchantUpdateRequest merchantUpdateRequest) {
        log.info("Updating merchant with id: {}", id);
        Merchant existingMerchant = getMerchantById(id);
        panelMapperInterface.updateMerchantFromRequest(merchantUpdateRequest, existingMerchant);
        return merchantRepository.save(existingMerchant);
    }

    @Override
    public Merchant getMerchantById(Long id) {
        return merchantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Merchant not found with id: " + id));
    }
    
    @Override
    public Page<Merchant> getAllMerchants(Pageable pageable) {
        log.info("Fetching all merchants");
        return merchantRepository.findAll(pageable);
    }


    private String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();
        byte[] randomBytes = new byte[18];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
