package com.myshopexample.service;

import com.myshopexample.model.bascket.Bascket;
import com.myshopexample.model.FavoriteList;
import com.myshopexample.model.customer.Customer;
import com.myshopexample.model.product.Product;
import com.myshopexample.model.bascket.BascketProduct;
import com.myshopexample.model.bascket.BascketProductKey;
import com.myshopexample.model.dto.BascketProductDto;
import com.myshopexample.model.dto.ProductDto;
import com.myshopexample.model.mapper.BascketProductMapper;
import com.myshopexample.model.mapper.ProductMapper;
import com.myshopexample.repositories.ProductRepository;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.bascket.BascketProductService;
import com.myshopexample.service.bascket.BascketService;
import com.myshopexample.service.security.PrincipalProvider;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class ProductsService {
    private final BascketProductMapper bascketProductMapper = Mappers.getMapper(BascketProductMapper.class);
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BascketService bascketService;
    @Autowired
    private FavoriteListService favoriteListService;
    @Autowired
    private BascketProductService bascketProductService;
    @Autowired
    private PrincipalProvider customerFromSpringSecurityContext;

//    public GenericResponse<ProductDto> saveProduct(ProductDto productDto){
//                Product product = productMapper.ProductDtoToProduct(productDto);
//                productRepository.save(product);
//                return new GenericResponse<>("success",productDto);
//    }

    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }
    public GenericResponse<Set<BascketProductDto>> addProductToBascket(Long productId, int quantity) {
            Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            Bascket bascket = bascketService.getBascketByCustomerId(customer.getId());
            BascketProductKey bascketProductKey = new BascketProductKey();
            bascketProductKey.setBascketId(bascket.getId());
            bascketProductKey.setProductId(productId);
            BascketProduct existentBascketProduct = bascketProductService.findById(bascketProductKey);
            if(existentBascketProduct != null){
                if(existentBascketProduct.getQuantity() + quantity >= 0)
                existentBascketProduct.setQuantity(existentBascketProduct.getQuantity() + quantity);
                else
                    existentBascketProduct.setQuantity(0);
                bascketProductService.saveNewBascketProduct(existentBascketProduct);
            }else {
                Product product = productRepository.findById(productId).get();
                BascketProduct bascketProduct = new BascketProduct();
                bascketProduct.setProduct(product);
                bascketProduct.setBascket(bascket);
                bascketProduct.setQuantity(quantity);
                bascketProductService.saveNewBascketProduct(bascketProduct);
            }
        Set<BascketProduct> bascketProducts = bascketProductService.findBascketProductsByBascketId(bascket.getId());
        Set<BascketProductDto> bascketProductDtos = bascketProductMapper.BascketProductToBascketProductDto(bascketProducts);
            return new GenericResponse<>("success",bascketProductDtos);
    }
    public GenericResponse<Set<ProductDto>> addProductToFavoriteList(Long product_id){
            Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            Product product = productRepository.findById(product_id).get();
            FavoriteList favoriteList = favoriteListService.findFavoriteListByCustomerId(customer.getId());
            favoriteList.addProduct(product);
            favoriteListService.saveFavoriteList(favoriteList);
            Set<Product> products = favoriteList.getFavoriteProducts();
            Set<ProductDto> productDtos = productMapper.ProductToProductDto(products);
            return new GenericResponse<>("success",productDtos);
    }

    public GenericResponse<Set<ProductDto>> getFavoriteProducts() {
            Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            FavoriteList favoriteList = favoriteListService.findFavoriteListByCustomerId(customer.getId());
            Set<Product> products = favoriteList.getFavoriteProducts();
            Set<ProductDto> productDtos = productMapper.ProductToProductDto(products);
            return new GenericResponse<>("success",productDtos);
    }

    public GenericResponse<Set<BascketProductDto>> getBascketProducts() {
        Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            Bascket bascket = bascketService.getBascketByCustomerId(customer.getId());
            Set<BascketProduct> bascketProducts = bascketProductService.findBascketProductsByBascketId(bascket.getId());
            Set<BascketProductDto> bascketProductDtos = bascketProductMapper.BascketProductToBascketProductDto(bascketProducts);
            return new GenericResponse<>("success", bascketProductDtos);
    }

    public Page<ProductDto> getProductsByCategory(String category, Pageable pageable) {
        Page<Product> productPage = productRepository.findProductsByCategory(category,pageable);
        Page<ProductDto> productDtoPage = productPage.map(productMapper::ProductToProductDto);
        return productDtoPage;
    }
    public GenericResponse<Set<BascketProductDto>> removeProductFromBascket(Long productId) {
            Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            Bascket bascket = bascketService.getBascketByCustomerId(customer.getId());
            BascketProductKey bascketProductKey = new BascketProductKey();
            bascketProductKey.setBascketId(bascket.getId());
            bascketProductKey.setProductId(productId);
            BascketProduct bascketProduct = bascketProductService.findById(bascketProductKey);
            bascket.removeBascketProduct(bascketProduct);
            bascketService.saveBascket(bascket);
            bascketProductService.removeBascketProduct(bascketProduct);
            Set<BascketProduct> bascketProducts = bascket.getBascketProducts();
            Set<BascketProductDto> bascketProductDtos = bascketProductMapper.BascketProductToBascketProductDto(bascketProducts);
            return new GenericResponse<>("success",bascketProductDtos);
    }

    public GenericResponse<Set<ProductDto>> removeProductFromFavoriteList(Long productId) {
            Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            FavoriteList favoriteList = favoriteListService.findFavoriteListByCustomerId(customer.getId());
            Product product = productRepository.findById(productId).get();
            favoriteList.removeProduct(product);
            favoriteListService.saveFavoriteList(favoriteList);
            Set<Product> products = favoriteList.getFavoriteProducts();
            Set<ProductDto> productDtos = productMapper.ProductToProductDto(products);
            return new GenericResponse<>("success",productDtos);
    }
}
