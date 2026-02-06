package com.core.merchant.panel.service;

import com.core.merchant.panel.entity.PaymentMethod;
import com.core.merchant.panel.model.PaymentMethodCreateRequest;
import com.core.merchant.panel.model.PaymentMethodUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentMethodService {
    PaymentMethod createPaymentMethod(PaymentMethodCreateRequest request);
    PaymentMethod updatePaymentMethod(Long id, PaymentMethodUpdateRequest request);
    PaymentMethod getPaymentMethodById(Long id);
    Page<PaymentMethod> getAllPaymentMethods(Pageable pageable);
}
