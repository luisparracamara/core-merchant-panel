package com.core.merchant.panel.mapper;

import com.core.merchant.panel.entity.Gateway;
import com.core.merchant.panel.entity.Merchant;
import com.core.merchant.panel.entity.PaymentMethod;
import com.core.merchant.panel.model.GatewayUpdateRequest;
import com.core.merchant.panel.model.MerchantUpdateRequest;
import com.core.merchant.panel.model.PaymentMethodUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PanelMapperInterface {

    // @MappingTarget le dice a MapStruct: "No crees un objeto nuevo, actualiza 'entity' con los datos de 'request'"
    void updateMerchantFromRequest(MerchantUpdateRequest request, @MappingTarget Merchant entity);

    void updatePaymentMethodFromRequest(PaymentMethodUpdateRequest request, @MappingTarget PaymentMethod entity);

    void updateGatewayFromRequest(GatewayUpdateRequest request, @MappingTarget Gateway entity);

}
