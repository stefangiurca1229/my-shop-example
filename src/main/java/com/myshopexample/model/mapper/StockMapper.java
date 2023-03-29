package com.myshopexample.model.mapper;

import com.myshopexample.model.Stock;
import com.myshopexample.model.dto.StockDto;
import org.mapstruct.Mapper;

@Mapper
public interface StockMapper {
    public StockDto StockToStockDto(Stock stock);
    public Stock StockDtoToStok(StockDto stockDto);
    public Stock test(StockDto stockDto);
}
