package com.saltsoftware.controller.dentalService;

import com.saltsoftware.entity.dentalService.ServiceCost;
import com.saltsoftware.factory.dentalService.ServiceCostFactory;
import org.junit.Assert;
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

/**
 * Created by :Lebusa Letsoha
 * Student no: 216059186
 * Date : 10/11/2020
 * Desc: testing of ServiceCost controller
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ServiceCostControllerTest {

    private static ServiceCost serviceCost = ServiceCostFactory.buildServiceCost("GUMClean441", "VVIP1011");

    private static String SECURITY_USERNAME = "BASIC";
    private static String SECURITY_PASSWORD = "3333";

    @Autowired
        private TestRestTemplate restTemplate;
        private String userURL = "http://localhost:8080/saltbookingmanagementapp/servicecost";

    @Test
    public void a_create() {
        String url = userURL + "create";

        ResponseEntity<ServiceCost> postResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .postForEntity(url,serviceCost,ServiceCost.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        serviceCost = postResponse.getBody();

        System.out.println("User role identity: "+serviceCost);
        System.out.println(postResponse);
        System.out.println(postResponse.getBody());

       Assert.assertEquals(HttpStatus.NOT_FOUND, postResponse.getStatusCode());

        Assert.assertEquals(serviceCost.getCostId(), serviceCost.getServiceId());
    }

        @Test
        public void read() {
            String url = userURL + "read" + serviceCost.getCostId();
            System.out.println("read " + serviceCost);

            ResponseEntity<ServiceCost> responseEntity = restTemplate
                    .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                    .getForEntity(url,ServiceCost.class);
            assertNotNull(responseEntity);
            assertNotNull(responseEntity.getBody());
        }

        @Test
        public void c_update() {
            ServiceCost updated = new ServiceCost.Builder().copy(serviceCost).costId("VVIP1011").serviceId("GUMClean441").build();
            String url = userURL + "update";
            ResponseEntity<ServiceCost> responseEntity = restTemplate
                    .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                    .postForEntity(url,updated, ServiceCost.class);
            assertNotNull(responseEntity);
            assertNotNull(updated);
            System.out.println("updated " + updated);
        }

    @Test
    public void e_delete() {
        String url = userURL +"delete/"+ serviceCost.getCostId();
        System.out.println("URL: "+url);
        restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .delete(url);
        }

        @Test
        public void d_getall() {
            String url = userURL + "all";

            System.out.println("URL: " + url);
            HttpHeaders headers = new HttpHeaders();

            HttpEntity<String> entity = new HttpEntity<>(null,headers);
            ResponseEntity<String> response = restTemplate
                    .exchange(url, HttpMethod.GET, entity,String.class);

            System.out.println(response);
            System.out.println(response.getBody());
            assertNotNull(response);
        }
}
