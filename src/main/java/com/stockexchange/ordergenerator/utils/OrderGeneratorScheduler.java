package com.stockexchange.ordergenerator.utils;

import com.stockexchange.ordergenerator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderGeneratorScheduler {
    @Autowired
    private OrderService orderService;

    @Scheduled(fixedDelayString = "${order.generator.fixed.delay}")
    void generateOrderJob() {
        orderService.generateOrder();
    }
}
