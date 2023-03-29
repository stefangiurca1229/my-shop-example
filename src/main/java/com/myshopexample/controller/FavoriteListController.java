package com.myshopexample.controller;

import com.myshopexample.model.dto.ProductDto;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
public class FavoriteListController {
    @Autowired
    private ProductsService productsService;
    @GetMapping("/get-favorite-products")
    public GenericResponse<Set<ProductDto>> getFavoriteProducts(){
        return productsService.getFavoriteProducts();
    }
    @PutMapping("/add-product-to-fovoriteList")
    public GenericResponse<Set<ProductDto>> addProductToFavoriteList(@RequestParam Long productId){
        return productsService.addProductToFavoriteList(productId);
    }
    @DeleteMapping("/remove-product-from-favoriteList")
    public GenericResponse<Set<ProductDto>> removeProductFromFavoriteList(
            @RequestParam Long productId
    ){
        return productsService.removeProductFromFavoriteList(productId);
    }
}
