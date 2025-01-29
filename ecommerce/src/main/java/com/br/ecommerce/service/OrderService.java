package com.br.ecommerce.service;

import com.br.ecommerce.dto.order.CreateOrderDTO;
import com.br.ecommerce.dto.order.ProductOrderDTO;
import com.br.ecommerce.dto.order.ResponseOrderDTO;
import com.br.ecommerce.dto.order.ResponseOrderProduct;
import com.br.ecommerce.entity.*;
import com.br.ecommerce.repository.OrderProductRepository;
import com.br.ecommerce.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, ProductService productService, UserService userService, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
        this.orderProductRepository = orderProductRepository;
    }


    public ResponseOrderDTO createOrder(String userId, CreateOrderDTO data){
        User user = userService.findById(userId);
        List<Product> listProducts = data.products().stream().map(
                p -> productService.findById(p.productId())
        ).toList();

        Order order = new Order();
        order.setUser(user);
        System.out.println("ID ORDER:"+ order.getId());

        List<OrderProduct> listOrderProducts = new ArrayList<>();


        for (ProductOrderDTO orderDTO : data.products()) {
            Product product = productService.findById(orderDTO.productId());
            checkStockAvailability(product, orderDTO.quantity());

            OrderProductId orderProductId = new OrderProductId(order.getId(), product.getId());

            OrderProduct orderProduct = new OrderProduct(orderProductId, product, order, orderDTO.quantity());
            listOrderProducts.add(orderProduct);
        }

        double totalAmount = listOrderProducts.stream()
                .mapToDouble(orderProduct -> orderProduct.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(orderProduct.getQuantity()))
                        .doubleValue())
                .sum();

        order.setOrderProducts(listOrderProducts);
        order.setTotalAmount(totalAmount);

        System.out.println("ID ORDER antes do save: " + order.getId());
        order = orderRepository.save(order);
        System.out.println("ID ORDER depois do save: " + order.getId());


        Order createdOrder = orderRepository.save(order);


        user.addOrders(createdOrder);

        orderProductRepository.saveAll(listOrderProducts);

        userService.save(user);

        return new ResponseOrderDTO(order, listOrderProducts(listOrderProducts));
    }

    public List<ResponseOrderProduct> listOrderProducts(List<OrderProduct> orderProducts) {
        List<ResponseOrderProduct> list = new ArrayList<>();
        for (OrderProduct oP : orderProducts) {
            list.add(new ResponseOrderProduct(oP.getProduct(), oP.getQuantity()));
        }
        return list;
    }

    public void checkStockAvailability(Product product, Long quantity) {
        if (product.getStockQuantity() < quantity) {
            throw  new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public Long getQuantityForProduct(Long productId, List<ProductOrderDTO> orderDTO) {
        return orderDTO.stream().filter(order -> order.productId().equals(productId))
                .mapToLong(ProductOrderDTO::quantity)
                .findFirst()
                .orElse(0);
    }
}
