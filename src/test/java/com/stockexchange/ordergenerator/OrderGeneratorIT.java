package com.stockexchange.ordergenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockexchange.ordergenerator.dto.OrderDto;
import com.stockexchange.ordergenerator.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
