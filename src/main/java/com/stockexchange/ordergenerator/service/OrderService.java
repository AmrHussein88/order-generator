package com.stockexchange.ordergenerator.service;

import com.stockexchange.ordergenerator.dto.OrderDto;

public interface OrderService {
    OrderDto generateOrder();
}
