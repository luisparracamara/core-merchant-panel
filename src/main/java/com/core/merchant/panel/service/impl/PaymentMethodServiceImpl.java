package com.core.merchant.panel.service.impl;

import com.core.merchant.panel.entity.PaymentMethod;
import com.core.merchant.panel.exception.BadRequestException;
import com.core.merchant.panel.exception.NotFoundException;
import com.core.merchant.panel.mapper.PanelMapperInterface;
import com.core.merchant.panel.model.PaymentMethodCreateRequest;
import com.core.merchant.panel.model.PaymentMethodUpdateRequest;
import com.core.merchant.panel.repository.PaymentMethodRepository;
import com.core.merchant.panel.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PanelMapperInterface panelMapperInterface;

    @Override
    @Transactional
    public PaymentMethod createPaymentMethod(PaymentMethodCreateRequest request) {

        Optional<PaymentMethod> existingPaymentMethod = paymentMethodRepository.findByCode(request.getCode()
                .toUpperCase(Locale.ROOT));
        if (existingPaymentMethod.isPresent()) {
            throw new BadRequestException("Payment method with code " + request.getCode() + " already exists");
        }
        
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .code(request.getCode().toUpperCase(Locale.ROOT))
                .logo(request.getLogo())
                .paymentType(request.getPaymentType())
                .country(request.getCountry().toUpperCase(Locale.ROOT))
                .description(request.getDescription())
                .status(request.getStatus())
                .name(request.getName())
                .provider(request.getProvider())
                .build();
                
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    @Transactional
    public PaymentMethod updatePaymentMethod(Long id, PaymentMethodUpdateRequest request) {
        PaymentMethod paymentMethod = getPaymentMethodById(id);
        panelMapperInterface.updatePaymentMethodFromRequest(request, paymentMethod);
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment method not found with id: " + id));
    }

    @Override
    public Page<PaymentMethod> getAllPaymentMethods(Pageable pageable) {
        return paymentMethodRepository.findAll(pageable);
    }

}
