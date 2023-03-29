package com.myshopexample.service.order;

import com.myshopexample.exception.InsufficientStockException;
import com.myshopexample.model.Stock;
import com.myshopexample.model.customer.Customer;
import com.myshopexample.model.dto.OrderDto;
import com.myshopexample.model.dto.OrderProductDto;
import com.myshopexample.model.mapper.OrderMapper;
import com.myshopexample.model.mapper.StockMapper;
import com.myshopexample.model.order.StatusOrder;
import com.myshopexample.model.product.Product;
import com.myshopexample.model.bascket.Bascket;
import com.myshopexample.model.bascket.BascketProduct;
import com.myshopexample.model.mapper.OrderProductMapper;
import com.myshopexample.model.order.Order;
import com.myshopexample.model.order.OrderProduct;
import com.myshopexample.repositories.ProductRepository;
import com.myshopexample.repositories.order.OrderRepository;
import com.myshopexample.responses.GenericResponse;
import com.myshopexample.service.CustomerService;
import com.myshopexample.service.StockService;
import com.myshopexample.service.bascket.BascketProductService;
import com.myshopexample.service.bascket.BascketService;
import com.myshopexample.service.security.PrincipalProvider;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderService {
    private final OrderProductMapper orderProductMapper = Mappers.getMapper(OrderProductMapper.class);
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    private final StockMapper stockMapper = Mappers.getMapper(StockMapper.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BascketProductService bascketProductService;
    @Autowired
    private StockService stockService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BascketService bascketService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private PrincipalProvider customerFromSpringSecurityContext;
    @Autowired
    private SendOrderToQueue sendOrderToQueue;

    @Autowired
    private ProductRepository productRepository;

    public GenericResponse<OrderDto> prepareOrder() {
            Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            sendOrderToQueue.sendOrder(customer);
//            Bascket bascket = bascketService.getBascketByCustomerId(customer.getId());
//            Set<BascketProduct> bascketProducts = bascketProductService.findBascketProductsByBascketId(bascket.getId());
//            Set<OrderProduct> orderProducts = new HashSet<>();
//            Order order = new Order();
//            order.setCustomer(customer);
//            order.setStatus(StatusOrder.PLACED);
//            order.setDateCreated(LocalDate.now());
//            orderRepository.save(order);
//            for(BascketProduct bascketProduct : bascketProducts){
//                Product product = bascketProduct.getProduct();
//                Double price = stockService.getPriceByProductId(product.getId());
//                OrderProduct orderProduct = orderProductMapper.ProductToOrderProduct(product);
//                orderProduct.setPrice(price);
//                orderProduct.setQuantity(bascketProduct.getQuantity());
//                orderProduct.setOrder(order);
//                orderProducts.add(orderProduct);
//                int numberOfstocksUpdated = stockService.updateStockByProduct(product, bascketProduct.getQuantity());
//                if(numberOfstocksUpdated == 0){
//                    throw new InsufficientStockException("insuficient Stock");
//                }
//            }
//            orderProductService.saveAll(orderProducts);
//            bascketService.removeBascket(bascket.getId());
//            OrderDto orderDto = orderMapper.OrderToOrderDto(order);
            return new GenericResponse<>("success",null);
    }

    public GenericResponse<List<OrderDto>> getOrders() {
            Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            List<Order> orders = orderRepository.findOrdersByCustomerId(customer.getId());
            List<OrderDto> orderDtos = orderMapper.OrderToOrderDto(orders);
            return new GenericResponse<>("success",orderDtos);
    }

    public GenericResponse<Set<OrderProductDto>> getOrderProductsFromOrder(Long orderId) {
            Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
            Order order = orderRepository.findOrderByCustomerIdAndId(customer.getName(),orderId);
            Set<OrderProduct> orderProducts = order.getOrderProducts();
            Set<OrderProductDto> orderProductDtos = orderProductMapper.OrderProductToOrderProductDto(orderProducts);
            return new GenericResponse<>("success",orderProductDtos);
    }

    public GenericResponse<OrderDto> prepareOrderWithPureJpa() {
        Customer customer = customerFromSpringSecurityContext.getPrincipalProvider();
        Bascket bascket = bascketService.getBascketByCustomerId(customer.getId());
        Set<BascketProduct> bascketProducts = bascketProductService.findBascketProductsByBascketId(bascket.getId());
        Set<OrderProduct> orderProducts = new HashSet<>();
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(StatusOrder.PLACED);
        order.setDateCreated(LocalDate.now());
        orderRepository.save(order);
        for(BascketProduct bascketProduct : bascketProducts){
            Product product = bascketProduct.getProduct();
            Double price = stockService.getPriceByProductId(product.getId());
            OrderProduct orderProduct = orderProductMapper.ProductToOrderProduct(product);
            orderProduct.setPrice(price);
            orderProduct.setQuantity(bascketProduct.getQuantity());
            orderProduct.setOrder(order);
            orderProducts.add(orderProduct);
            Stock stock = stockService.findStockByProductId(product.getId());
            if(stock.getQuantity() - bascketProduct.getQuantity() >= 0){
                stock.setQuantity(stock.getQuantity() - bascketProduct.getQuantity());
                stockService.save(stock);
            }else{
                throw new InsufficientStockException("insuficient Stock");
            }
        }
        orderProductService.saveAll(orderProducts);
        bascketService.removeBascket(bascket.getId());
        OrderDto orderDto = orderMapper.OrderToOrderDto(order);
        return new GenericResponse<>("success",orderDto);
    }
}
