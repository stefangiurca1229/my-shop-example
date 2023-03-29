package com.myshopexample.model.mapper;

import com.myshopexample.model.product.Product;
import com.myshopexample.model.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface ProductMapper {
    public ProductDto ProductToProductDto(Product product);
    public Set<ProductDto> ProductToProductDto(Set<Product> product);
    public Product ProductDtoToProduct(ProductDto productDto);
}
