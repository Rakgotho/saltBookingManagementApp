package com.saltsoftware.controller.employee;

import com.saltsoftware.entity.employee.EmployeeRole;
import com.saltsoftware.factory.employee.EmployeeRoleFactory;
import com.saltsoftware.util.GenericHelper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by :Sakeena Francis
 * Student no: 215006097
 * Desc: testing of EmployeeRole controller
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EmployeeRoleControllerTest {

        private static String empId = GenericHelper.generateID();

        private static EmployeeRole employeeRole = EmployeeRoleFactory.buildEmployeeRole("556566","55576");
        private static String SECURITY_USERNAME = "SUPER";
        private static String SECURITY_PASSWORD = "5555";

        @Autowired
        private TestRestTemplate restTemplate;
        private String myURL = "http://localhost:8080/employeeRole/";

        //create
        @Test
        public void a_create() {
                String url = myURL + "create/";
                System.out.println("URL: "+url);
                System.out.println("Post data: "+employeeRole);
                System.out.println("ID: "+ employeeRole.getEmpID());

                ResponseEntity<EmployeeRole> postResponse = restTemplate
                        .withBasicAuth("SUPER","5555")
                        .postForEntity(url,employeeRole,EmployeeRole.class);

                //assertNotNull(postResponse);
                //assertNotNull(postResponse.getBody());
                //employeeRole = postResponse.getBody();

                System.out.println("Posted Data: "+employeeRole);
                System.out.println(postResponse);
                System.out.println(postResponse.getBody());

                //assertEquals(HttpStatus.FORBIDDEN, postResponse.getStatusCode());
                //assertEquals(employeeRole.getEmpID(), employeeRole.getRoleID());
        }

//reading the employeerole
/*@Test
public void b_read() {
        String url = myURL + "read/" + employeeRole.getEmpID();
        System.out.println("Read:  " + url);
        restTemplate.getRestTemplate()
                .getForObject(myURL + "/employeeRole/read", EmployeeRole.class);
        System.out.println(employeeRole.getEmpID());
        assertNotNull(employeeRole);
*/
        @Test
        public void b_read() {
         String url = myURL + "read/" + employeeRole.getEmpID();
         System.out.println("read " + employeeRole);
        ResponseEntity<EmployeeRole> responseEntity = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .getForEntity(url,EmployeeRole.class);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        }

        /*@Test
        public void e_delete() {
                String url = myURL +"delete/"+ employeeRole.getEmpID();
                employeeRole.getRoleID();
                System.out.println("URL: "+url);
                restTemplate
                        .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                        .delete(url);
        }*/

        @Test //deleting employee via id
        public void e_delete() {
                String empId = null;
                EmployeeRole employeeRole = restTemplate
                        .withBasicAuth("SUPER","5555")
                        .getForObject(myURL + "/employeeRole//" + empId, EmployeeRole.class);

                assertNotNull(employeeRole);
                restTemplate.delete(myURL + "/employeeRole/" + empId);

                try     {
                        restTemplate.getForObject(myURL + "/employeeRole/" + empId, EmployeeRole.class);
                        } catch (final HttpClientErrorException e) {
                        assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
                }
        }

@Test
public void d_getAll() {
        String url = myURL + "all";
        System.out.println("Get all" + employeeRole);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity <String> responseEntity = restTemplate
                .withBasicAuth("SUPER","5555")
                .exchange(url, HttpMethod.GET,entity, String.class );
        System.out.println(responseEntity.getBody());
        assertNotNull(responseEntity);

        }
}