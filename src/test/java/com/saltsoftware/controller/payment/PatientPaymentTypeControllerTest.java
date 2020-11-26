package com.saltsoftware.controller.payment;


import com.saltsoftware.entity.payment.PatientPaymentType;
import com.saltsoftware.factory.payment.PatientPaymentTypeFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

//random port to avoid an already used port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PatientPaymentTypeControllerTest {

    private static PatientPaymentType paymentTypeService = PatientPaymentTypeFactory.createPaymentType("EFT");
    private static String SECURITY_USERNAME = "SUPER";
    private static String SECURITY_PASSWORD = "5555";

    @Autowired
    private TestRestTemplate restTemplate = null;
    private String baseURL = "http://localhost:8989/paymenttype/";

    //Test case for create method
    @Test
    public void a_create() {
        String url = baseURL + "create/";
        System.out.println("URL: "+ url);
        System.out.println("Post data: "+ paymentTypeService);
        ResponseEntity<PatientPaymentType> postResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .postForEntity(url, paymentTypeService,PatientPaymentType.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        paymentTypeService = postResponse.getBody();
        System.out.println("Saved data: "+ paymentTypeService);
        assertEquals(paymentTypeService.getPaymentTypeID(), postResponse.getBody().getPaymentTypeID());


    }

    @Test
    public void b_read() {
        String url = baseURL + "read/"+ paymentTypeService.getPaymentTypeID();
        System.out.println("URL "+ url);
        ResponseEntity<PatientPaymentType> getResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .getForEntity(url, PatientPaymentType.class);
        assertEquals(paymentTypeService.getPaymentTypeID(), getResponse.getBody().getPaymentTypeID());
       System.out.println("this is response--> "+ paymentTypeService);
    }

    //Test case for update method
    @Test
    public void c_update() {
        PatientPaymentType updated = new PatientPaymentType.Builder().copy(paymentTypeService).setDescrip("Cash Payment").build();
        String url = baseURL + "update/";
        System.out.println("Url "+ url);
        System.out.println("Post data: " + updated);
        paymentTypeService = updated;
        ResponseEntity<PatientPaymentType> response = restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .postForEntity(url,updated,PatientPaymentType.class);
        assertEquals(paymentTypeService.getPaymentTypeID(), response.getBody().getPaymentTypeID());
        //assertNotNull(paymentTypeService);
        //assertNotNull(updated);
        System.out.println("After update: "+ paymentTypeService);
    }

    //Test case for getAll method
    @Test
    public void d_getall() {
        String url = baseURL + "all";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response);
        System.out.println(response.getBody());
    }

    //Test case for delete method
    @Test
    public void e_delete() {
        String url = baseURL + "/delete/" + paymentTypeService.getPaymentTypeID();
        System.out.println("URL: "+ url);
        restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .delete(url);
        System.out.println("after delete: "+paymentTypeService);

    }
}