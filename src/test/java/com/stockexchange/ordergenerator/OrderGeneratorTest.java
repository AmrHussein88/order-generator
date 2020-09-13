package com.stockexchange.ordergenerator;

import com.stockexchange.ordergenerator.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class OrderGeneratorTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OrderDto orderDto = new OrderDto();
    @Test
    public void generateOrder_Test(){
        Mockito.when(restTemplate.postForEntity("http://localhost:8080/orders", orderDto, OrderDto.class))
        .thenReturn(new ResponseEntity<>(orderDto, HttpStatus.OK));
    }

    @Test
    public void generateOrder_TestIT(){
        Map<String, String> json = new HashMap<>();
        json.put("symbol", "APPLE");
        json.put("quantity", "2");
//        RestAssured.with().body(json).when()
//                .request("POST", "http://localhost:8080/orders").then().statusCode(200);
//      Response body =  RestAssured.given().contentType(ContentType.JSON).body(json)
//                .when().post("http://localhost:8080/orders").then().extract().response();
//        Assert.assertEquals(200, body.getStatusCode());
    }

}
