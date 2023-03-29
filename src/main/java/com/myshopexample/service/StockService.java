package com.myshopexample.service;

import com.myshopexample.model.product.Product;
import com.myshopexample.model.Stock;
import com.myshopexample.model.dto.StockDto;
import com.myshopexample.model.mapper.StockMapper;
import com.myshopexample.repositories.StockRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private final StockMapper stockMapper = Mappers.getMapper(StockMapper.class);
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductsService productsService;
//    public GenericResponse<StockDto> addStock(StockDto stockDto, Long productId){
//                Product product = productsService.getProductById(productId);
//                LocalDate localDate = LocalDate.now();
//                stockDto.setCreateDate(localDate);
//                Stock stock = stockMapper.StockDtoToStok(stockDto);
//                stock.setProduct(product);
//                stockRepository.save(stock);
//                stockDto.setId(stock.getId());
//                return new GenericResponse<>("success",stockDto);
//    }
//
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public StockDto findStockDtoByProductId(Long productId){
        Stock stock = stockRepository.findFirstByEnableAndProduct_IdOrderByCreateDateAsc(true,productId);
        return stockMapper.StockToStockDto(stock);
    }

    public Stock findStockByProductId(Long productId){
        return stockRepository.findFirstByEnableAndProduct_IdOrderByCreateDateAsc(true,productId);
    }

    public int updateStockByProduct(Product product, int quantity) {
        Stock stock = stockRepository.findFirstByEnableAndProduct_IdOrderByCreateDateAsc(true, product.getId());
        return stockRepository.updateStock(quantity, stock.getId());
    }

    public Double getPriceByProductId(Long id) {
        return stockRepository.findPriceByProduct_IdAndEnable(id,true);
    }
}
