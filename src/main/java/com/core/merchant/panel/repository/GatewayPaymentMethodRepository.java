package com.core.merchant.panel.repository;

import com.core.merchant.panel.entity.GatewayPaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayPaymentMethodRepository extends JpaRepository<GatewayPaymentMethod, Long> {
    Page<GatewayPaymentMethod> findByGatewayId(Long gatewayId, Pageable pageable);
}
