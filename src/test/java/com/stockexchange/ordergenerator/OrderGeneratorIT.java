package com.stockexchange.ordergenerator;

import com.stockexchange.ordergenerator.dto.OrderDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderGeneratorIT {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private OrderDto orderDto = new OrderDto();

    @Test
    public void generateOrder_Test() {
        Mockito.when(restTemplate.postForEntity("http://localhost:8080/orders", orderDto, OrderDto.class))
                .thenReturn(new ResponseEntity<>(orderDto, HttpStatus.OK));
        ResponseEntity<OrderDto> orderResponse = restTemplate.postForEntity("http://localhost:8080/orders", orderDto, OrderDto.class);
        Assert.assertNotNull(orderResponse);
        Assert.assertEquals(200, orderResponse.getStatusCode().value());
    }
}
