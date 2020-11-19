package com.saltsoftware.controller.payment;

import com.saltsoftware.entity.employee.Employee;
import com.saltsoftware.entity.payment.PatientPaymentRecord;
import com.saltsoftware.factory.payment.PatientPaymentRecordFactory;
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
//random port to avoid an already used port


/*
    Author: Linton Appollis
    Student no: 216182484
    Desc: Test cases for Patient Payment Record Controller with Security
    Date: 27 Sept and 09 November
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PatientPaymentRecordControllerTest

{
    //Testing Super Role
    private static PatientPaymentRecord patientPaymentRecord = PatientPaymentRecordFactory.createPatientPaymentRecord("00001","1 December ","R10 000");

    private static String SECURITY_USERNAME = "SUPER";
    private static String SECURITY_PASSWORD = "5555";

    @Autowired
    private TestRestTemplate restTemplate = null;
    private String myURL = "http://localhost:8080/paymentrecord/";

    //Testing if I can create a Patient Payment Record
    @Test
    public void a_create() {
        String url = myURL + "create";
        System.out.println("URL:"+url);
        System.out.println("Post data "+ patientPaymentRecord);
        ResponseEntity<PatientPaymentRecord> postResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .postForEntity(url,patientPaymentRecord,PatientPaymentRecord.class);
        assertNotNull(postResponse);
        Assert.assertEquals(patientPaymentRecord.getPayReceiptNumber(),postResponse.getBody().getPayDate(), postResponse.getBody().getPayDate());
        patientPaymentRecord = postResponse.getBody();
        System.out.println("Saved data: "+patientPaymentRecord);
        System.out.println(postResponse);
    }

    //Testing if I can read data for Patient Payment Record
    @Test
    public void b_read() {
        String url = myURL + "read/"+ patientPaymentRecord.getPayReceiptNumber();
        System.out.println("URL "+ url);
        System.out.println("from read:  "+patientPaymentRecord);
        System.out.println(url);
        restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .getRestTemplate()
                .getForObject(url +"/patientpaymentrecord/read",PatientPaymentRecord.class);
        System.out.println("from read response: "+patientPaymentRecord);
        System.out.println(patientPaymentRecord.getPayAmount());
        assertNotNull(patientPaymentRecord);
    }
    //Testing if I can successfully update a Patient Payment Record
    @Test
    public void c_update() {
        System.out.println("from pre value for payment receipt : "+patientPaymentRecord.getPayReceiptNumber()+" "+patientPaymentRecord.getPayDate()+" "+patientPaymentRecord.getPayAmount());
        PatientPaymentRecord updated = new
                PatientPaymentRecord.Builder().copy(patientPaymentRecord).setpayReceiptNumber("987623454").build();
        String url = myURL + "update";
        System.out.println("url "+ url);
        patientPaymentRecord = updated;
        ResponseEntity<PatientPaymentRecord> response =
                restTemplate
                        .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                        .postForEntity(url,updated,PatientPaymentRecord.class);
        assertNotEquals(updated.getPayReceiptNumber(),response.getBody().getPayReceiptNumber());
        System.out.println("post value for payment receipt : "+patientPaymentRecord.getPayReceiptNumber()+" "+patientPaymentRecord.getPayDate()+" "+ patientPaymentRecord.getPayAmount());

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
    //Testing if I can successfully drop a Patient Payment Record
    @Test
    public void e_delete() {
        String url = myURL +"/delete/"+ patientPaymentRecord.getPayReceiptNumber();
        System.out.println("URL: "+url);
        restTemplate
                .withBasicAuth(SECURITY_USERNAME,SECURITY_PASSWORD)
                .delete(url);
        System.out.println("after delete: "+patientPaymentRecord);


    }


}