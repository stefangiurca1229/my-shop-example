package com.myshopexample.controller;

import com.myshopexample.model.dto.BascketProductDto;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
public class BascketController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/get-bascket-products")
    public GenericResponse<Set<BascketProductDto>> getBascketProducts(){
        return productsService.getBascketProducts();
    }
    @PutMapping("/add-product-to-bascket")
    public GenericResponse<Set<BascketProductDto>> addProductToBascket(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity
    ){
        return productsService.addProductToBascket(productId,quantity);
    }
    @DeleteMapping("/remove-product-from-bascket")
    public GenericResponse<Set<BascketProductDto>> removeProductFromBascket(
            @RequestParam Long productId
    ){
        return productsService.removeProductFromBascket(productId);
    }
}
