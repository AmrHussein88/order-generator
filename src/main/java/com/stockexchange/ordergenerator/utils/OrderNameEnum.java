package com.stockexchange.ordergenerator.utils;

public enum OrderNameEnum {
    APPLE("APPLE"),
    HUAWEI("HUAWEI"),
    SAMSUNG("SAMSUNG");
    private final String orderName;

    OrderNameEnum(final String orderName) {
        this.orderName = orderName;
    }
}
