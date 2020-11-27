package com.saltsoftware.controller.dentalService;

import com.saltsoftware.entity.dentalService.Service;
import com.saltsoftware.factory.dentalService.ServiceFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


/* Student: Abram Rakgotho
 * Student Number: 215031393
 * Group: Part time
 * Role: Testing the functionality of serviceController
 */

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ServiceControllerTest {

    private static Service service = ServiceFactory.createService("Dentist", "Teeth Alignment");
    private static final String USERNAME = "man1";
    private static final String PASSWORD = "password";

    @Autowired
    private TestRestTemplate restTemp;
    private String baseURL = "Http://localhost:8080/service/";

    @Test
    public void a_create()
    {

        String url = baseURL + "create";
        System.out.println("URL: " + url);
        System.out.println("Post data "+ service);
        ResponseEntity<Service> postResponse = restTemp.withBasicAuth(USERNAME, PASSWORD)
                .postForEntity(url, service, Service.class);
        assertNotNull(postResponse);
        Assert.assertEquals(service.getServiceName(),postResponse.getBody().getServiceName());
        service = postResponse.getBody();
        System.out.println("Saved data: "+service);
        System.out.println(postResponse);

    }
    @Test
    public void b_read()
    {

        String url = baseURL + "read/" + service.getServiceId();
        System.out.println("URL "+ url);
        System.out.println("read  " + service);
        restTemp
                .withBasicAuth(USERNAME,PASSWORD)
                .getRestTemplate()
                .getForObject(url +"/service", Service.class);
        System.out.println(service.getServiceName());
        Assert.assertNotNull(service);


        /*Service service = restTemp.withBasicAuth("service","service")//just creating a basic authentication
                .getForObject(url + "/service", Service.class);
        System.out.println(service.getServiceId());
        assertNotNull(service);*/

    }
    @Test
    public void c_update()
    {
        Service updated = new Service.Builder().copy(service).setServiceName("Dentist")
                .setServiceDescrip("Teeth removal").build();
        String url = baseURL + "update";
        System.out.println("URL: " + url); //This printout enables me to literally display my URL to the output.
        ResponseEntity<Service> response = restTemp
                .withBasicAuth(USERNAME, PASSWORD)
                .postForEntity(url, updated, Service.class);
        assertEquals(service.getServiceId(), response.getBody().getServiceId());
        System.out.println(response);

    }
    @Test
    public void d_getAll()
    {
        //The server has not found anything matching the requestit  URL
        String url = baseURL + " all";
        System.out.println("URL: " +url);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemp
                .withBasicAuth(USERNAME,PASSWORD).exchange(url,
                HttpMethod.GET, entity, String.class);

        System.out.println(response.getBody());
        Assert.assertNotNull(response);





    }
    @Test
    public void e_delete()
    {

        String url = baseURL + "delete/" + service.getServiceId();
        System.out.println("URL: " +url);
        restTemp.withBasicAuth(USERNAME, PASSWORD).delete(url);


    }

}