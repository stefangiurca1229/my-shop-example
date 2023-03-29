package com.myshopexample.model.mapper;

import com.myshopexample.model.bascket.BascketProduct;
import com.myshopexample.model.dto.BascketProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper
public interface BascketProductMapper {
    @Mapping(target = "productDto",source = "bascketProduct.product")
    BascketProductDto BascketProductToBascketProductDto(BascketProduct bascketProduct);
    Set<BascketProductDto> BascketProductToBascketProductDto(Set<BascketProduct> bascketProducts);
    BascketProduct BascketProductDtoToBascketProduct(BascketProductDto bascketProductDto);
}
