package com.myshopexample.model.mapper;

import com.myshopexample.model.product.Product;
import com.myshopexample.model.dto.OrderProductDto;
import com.myshopexample.model.order.OrderProduct;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface OrderProductMapper {
    public OrderProductDto OrderProductToOrderProductDto(OrderProduct orderProduct);
    public Set<OrderProductDto> OrderProductToOrderProductDto(Set<OrderProduct> orderProducts);
    public OrderProduct OrderProductDtoToOrderProduct(OrderProductDto orderProductDto);
    public OrderProduct ProductToOrderProduct(Product product);
}
