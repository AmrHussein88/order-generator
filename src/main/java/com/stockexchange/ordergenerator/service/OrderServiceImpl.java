package com.stockexchange.ordergenerator.service;

import com.stockexchange.ordergenerator.dto.OrderDto;
import com.stockexchange.ordergenerator.utils.OrderNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Component
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${order.processor.base.url}")
    private String url;
    @Value("${order.processor.processOrder.endpoint}")
    private String processOrderEndpoint;

    @Override
    public OrderDto generateOrder() {
        logger.info("Start generating new order");

        OrderDto order = createOrder();
        String orderProcessUrl = url + processOrderEndpoint;
        OrderDto orderDto = restTemplate.postForObject(orderProcessUrl, order, OrderDto.class);
        System.out.println("New processed order is " + orderDto);
        return orderDto;
    }

    private OrderDto createOrder() {
        //Randomly pick order symbol and assign random quantity
        Random random = new Random();
        int range = random.nextInt((10 - 1) + 1) + 1;
        OrderDto order = new OrderDto();
        List<OrderNameEnum> names = Arrays.stream(OrderNameEnum.values()).collect(Collectors.toList());
        order.setSymbol(names.get(random.nextInt(names.size())).name());
        order.setQuantity(range);
        return order;
    }

}
