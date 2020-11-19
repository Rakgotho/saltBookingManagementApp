package com.saltsoftware.controller.employee;

import com.saltsoftware.entity.employee.Employee;
import com.saltsoftware.entity.employee.EmployeeRole;
import com.saltsoftware.entity.payment.PatientPaymentType;
import com.saltsoftware.factory.employee.EmployeeFactory;
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
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.*;
/*
    Author: Abduragmaan Frank
    Stud no: 217009069
    Desc: Test cases for Employee Controller WITH Security
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {

    private static Employee employee = EmployeeFactory.createEmployee("Abduragmaan","Fronk");

    private static String SECURITY_USERNAME = "SUPER";
    private static String SECURITY_PASSWORD = "5555";

    @Autowired
    private TestRestTemplate restTemplate = null;
    private String myURL = "http://localhost:8080/employee/";

    @Test
    public void a_create() {
    String url = myURL + "create";
        System.out.println("URL:"+url);
        System.out.println("Post data "+ employee);
        ResponseEntity<Employee> postResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .postForEntity(url,employee,Employee.class);
        assertNotNull(postResponse);
        Assert.assertEquals(employee.getEmpName(),postResponse.getBody().getEmpName());
        employee = postResponse.getBody();
        System.out.println("Saved data: "+employee);
        System.out.println(postResponse);
    }


    @Test
    public void d_getAll() {
        String url = myURL + "all";
        System.out.println("URL "+ url);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());
        assertNotNull(response);
    }

    @Test
    public void b_read() {
        String url = myURL + "read/"+ employee.getEmpId();
        System.out.println("URL "+ url);
        System.out.println("from read:  "+employee);
        System.out.println(url);
        restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .getRestTemplate()
                .getForObject(url +"/employee/read",Employee.class);
        System.out.println("from read response: "+employee);
        System.out.println(employee.getEmpName());
        assertNotNull(employee);
    }

    //Testing if i can successfully update my LastName
    @Test
    public void c_update() {
        System.out.println("from pre updated : "+employee.getEmpName()+" "+employee.getEmpLastName()+" "+employee.getEmpId());
        Employee updated = new
                Employee.Builder().copy(employee).setempLastName("Frank").build();
        String url = myURL + "update";
       // System.out.println("from updated: "+updated);
        System.out.println("url "+ url);
      //  System.out.println(updated.getEmpName()+""+updated.getEmpLastName()+""+updated.getEmpId());


        employee = updated;
        ResponseEntity<Employee> response =
                restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .postForEntity(url,updated,Employee.class);
        //        assertNotNull(response);
        assertNotEquals(updated.getEmpLastName(),response.getBody().getEmpLastName());
            //    assertNotNull(updated);
       // System.out.println(updated.getEmpName()+updated.getEmpLastName());
        System.out.println("from post updated : "+employee.getEmpName()+" "+employee.getEmpLastName()+" "+employee.getEmpId());

    }


    @Test
    public void e_delete() {
        String url = myURL +"/delete/"+ employee.getEmpId();
        System.out.println("URL: "+url);
        restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .delete(url);
        System.out.println("after delete: "+employee);


    }

}