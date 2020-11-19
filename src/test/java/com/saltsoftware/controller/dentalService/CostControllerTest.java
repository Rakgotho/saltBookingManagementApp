package com.saltsoftware.controller.dentalService;

import com.saltsoftware.entity.dentalService.Cost;
import com.saltsoftware.factory.dentalService.CostFactory;
import com.saltsoftware.service.dentalService.impl.CostServiceImpl;
import com.saltsoftware.service.dentalService.CostService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by :Junade Frizlar
 * Student number: 208046402
 * Description: testing of Cost controller
 */

//random port to avoid an already used port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class CostControllerTest {

    private Cost cost = CostFactory.createCost("145698",60.00);
    private static String SECURITY_USERNAME = "SUPER";
    private static String SECURITY_PASSWORD = "8888";

    @Autowired
    private TestRestTemplate restTemplate;
    private String baseURL = "http://localhost:8080/cost";

    //Test case for create method
    @Test
    public void a_create() {
        String url = baseURL + "create";
        System.out.println("URL: "+url);
        System.out.println("Post data: "+cost);

        ResponseEntity<Cost> postResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .postForEntity(url, cost,Cost.class);
        Assertions.assertNotNull(postResponse);
        Assertions.assertNotNull(postResponse.getBody());
        cost = postResponse.getBody();

        System.out.println("Your Cost is: "+cost);
        System.out.println(postResponse);
        System.out.println(postResponse.getBody());
        assertEquals(cost.getCostID(), cost.getAmount(), postResponse.getBody().getCostID(), postResponse.getBody().getAmount());

    }

    private void assertEquals(String costID, double amount, String costID1, double amount1) {
    }

    //reading the Cost
    @Test
    public void b_read() {
        String url = baseURL + "read" + cost.getCostID();
        System.out.println("URL " + url);
        ResponseEntity<Cost> getResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .getForEntity(url,Cost.class);
        System.out.println("this is response--> "+getResponse);
        Assertions.assertNotNull(getResponse);
        Assertions.assertNotNull(getResponse.getBody());
    }

    //Test case for update method
    @Test
    public void c_update() {
        Cost updated = new Cost.Builder().copy(cost).setCostID("60").setAmount(40.00).build();
        String url = baseURL + "/update";
        System.out.println("url "+ url);
        ResponseEntity<Cost> response = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .postForEntity(url,updated,Cost.class);
        Assertions.assertNotNull(cost);
        Assertions.assertNotNull(updated);
        System.out.println("updated " + cost);
    }

    //Test case for delete method
    @Test
    public void e_delete() {
        String url = baseURL +"/delete/"+ cost.getCostID();
        System.out.println("URL: "+url);
        restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .delete(url);
        System.out.println("after delete: "+cost);
    }

    //Test case for getAll method
    @Test
    public void d_getAll() {
        String url = baseURL + "all";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity <String> response = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .exchange(url, HttpMethod.GET,entity, String.class );
        System.out.println(response);
        System.out.println(response.getBody());

    }
}