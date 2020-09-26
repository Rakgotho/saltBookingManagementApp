package com.saltsoftware.controller.dentalService;

import com.saltsoftware.entity.dentalService.Service;
import com.saltsoftware.factory.dentalService.ServiceFactory;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ServiceControllerTest {
    private static Service service = ServiceFactory.createService("Dentist", "Teeth Alignment");

    @Autowired
    private TestRestTemplate restTemp;
    private String baseURL = "Http://localhost:8080/service/";

    @Test
    public void a_create()
    {
        //Service service = ServiceFactory.createService("Dentist", "Teeth alignment");
        String url = baseURL + "create";
        System.out.println("URL: " + url);
        ResponseEntity<Service> postResponse = restTemp.postForEntity(url, service, Service.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println(postResponse.getBody());
    }
    @Test
    public void b_read()
    {
        String url = baseURL + "read/" + service.getServiceId();
        System.out.println("URL: " +url);
        Service service = restTemp.withBasicAuth("service","service")
                .getForObject(url + "/service", Service.class);
        System.out.println(service.getServiceId());
        assertNotNull(service);

    }
    @Test
    public void c_update()
    {
        Service updated = new Service.Builder().copy(service).setServiceName("Dentist")
                .setServiceDescrip("Teeth removal").build();
        String url = baseURL + "update";
        System.out.println("URL: " + url);
        ResponseEntity<Service> response = restTemp.postForEntity(url, service, Service.class);
        assertEquals(service.getServiceId(), response.getBody().getServiceId());
        System.out.println(updated);

    }
    @Test
    public void d_getAll()
    {
        String url = baseURL + " all";
        System.out.println("URL: " +url);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemp.exchange(url,
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());


    }
    @Test
    public void e_delete()
    {

        String url = baseURL + "delete/" + service.getServiceId();
        System.out.println("URL: " +url);
        restTemp.delete(url);


    }

}