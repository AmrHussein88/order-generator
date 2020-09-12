package com.stockexchange.ordergenerator.service;

import com.stockexchange.ordergenerator.utils.OrderDto;
import com.stockexchange.ordergenerator.utils.OrderNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Component
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${order.processor.url}")
    private String url;

    @Override
    @Scheduled(fixedDelay = 2000)
    public void generateOrder() {
        logger.info("Start generating new order");

        OrderDto order = createOrder();
        String orderProcessUrl = url + "orderProcessor/processOrder" ;
        OrderDto orderDto = restTemplate.postForObject(orderProcessUrl, order, OrderDto.class);
        System.out.println("New processed order is "+ orderDto);
    }

    private OrderDto createOrder() {
        Random random = new Random();
        int range = random.nextInt((10-1) +1)+1 ;
        OrderDto order = new OrderDto();
        List<OrderNameEnum> names = Arrays.stream(OrderNameEnum.values()).collect(Collectors.toList());
        order.setSymbol(names.get(random.nextInt(names.size())).name());
        order.setQuantity(range);
        System.out.println("Generated order is " + order);
        return order;
    }

}
