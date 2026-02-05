package com.core.merchant.panel.mapper;

import com.core.merchant.panel.entity.Merchant;
import com.core.merchant.panel.model.MerchantUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MerchantMapperInterface {

    // @MappingTarget le dice a MapStruct: "No crees un objeto nuevo, actualiza 'entity' con los datos de 'request'"
    void updateMerchantFromRequest(MerchantUpdateRequest request, @MappingTarget Merchant entity);

}
