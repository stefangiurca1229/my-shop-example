package com.myshopexample.service.order;

import com.myshopexample.exception.InsufficientStockException;
import com.myshopexample.model.bascket.Bascket;
import com.myshopexample.model.bascket.BascketProduct;
import com.myshopexample.model.customer.Customer;
import com.myshopexample.model.dto.OrderDto;
import com.myshopexample.model.mapper.OrderMapper;
import com.myshopexample.model.mapper.OrderProductMapper;
import com.myshopexample.model.order.Order;
import com.myshopexample.model.order.OrderProduct;
import com.myshopexample.model.order.StatusOrder;
import com.myshopexample.model.product.Product;
import com.myshopexample.repositories.order.OrderRepository;
import com.myshopexample.service.StockService;
import com.myshopexample.service.bascket.BascketProductService;
import com.myshopexample.service.bascket.BascketService;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RabbitListener(queues = "orders")
public class ResolveOrder {
    @Autowired
    private BascketService bascketService;
    @Autowired
    private BascketProductService bascketProductService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StockService stockService;
    private final OrderProductMapper orderProductMapper = Mappers.getMapper(OrderProductMapper.class);
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    @RabbitHandler
    @Transactional
    public void resolveOrder(Customer customer){
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
            int numberOfstocksUpdated = stockService.updateStockByProduct(product, bascketProduct.getQuantity());
            if(numberOfstocksUpdated == 0){
                throw new InsufficientStockException("insuficient Stock");
            }
        }
        orderProductService.saveAll(orderProducts);
        bascketService.removeBascket(bascket.getId());
        OrderDto orderDto = orderMapper.OrderToOrderDto(order);
    }
}
