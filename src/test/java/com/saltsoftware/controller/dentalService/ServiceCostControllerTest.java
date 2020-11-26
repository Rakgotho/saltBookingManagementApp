package com.saltsoftware.controller.dentalService;

import com.saltsoftware.entity.dentalService.ServiceCost;
import com.saltsoftware.entity.employee.EmployeeRole;
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
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

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

    private static ServiceCost serviceCost = ServiceCostFactory.buildServiceCost("GUMClean441", "ServiceCost@2d7637e6");

    private static String SECURITY_USERNAME = "SUPER";
    private static String SECURITY_PASSWORD = "5555";

    @Autowired
    private TestRestTemplate restTemplate;
    private String userURL = "http://localhost:8080/servicecost";

    @Test
    public void a_create() {
        String url = userURL + "create/";

        ResponseEntity<ServiceCost> postResponse = restTemplate
                .withBasicAuth("SUPER","5555")
                .postForEntity(url,serviceCost,ServiceCost.class);

        System.out.println("User role identity: "+ serviceCost);
    }

        @Test
        public void b_read() {
            String url = userURL + "read" + serviceCost.getCostId();
            System.out.println("read " + serviceCost);
        }

        @Test
        public void c_update() {
            ServiceCost updated = new ServiceCost.Builder().copy(serviceCost).costId("ServiceCost@2d7637e6").serviceId("GUMClean441").build();
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
        }

        @Test
        @GetMapping("/all")
        public void d_getall() {
            String url = userURL + " all";

            System.out.println("Get all" + serviceCost);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(null,headers);
            ResponseEntity <String> responseEntity = restTemplate
                    .withBasicAuth("SUPER","5555")
                    .exchange(url, HttpMethod.GET,entity, String.class );
        }
}
