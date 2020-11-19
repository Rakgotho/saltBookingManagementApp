package com.saltsoftware.controller.bookingSchedule;

import com.saltsoftware.entity.bookingSchedule.BookingSchedule;
import com.saltsoftware.factory.bookingSchedule.BookingScheduleFactory;
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
 * Created by :Craig Carr AKA Fish84
 * Student no: 204111307
 * Desc: testing of BookingSchedule controller
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BookingScheduleControllerTest {

    private static BookingSchedule bookingSchedule = BookingScheduleFactory.createBookingSchedule("10-02-2020", "13:00");
    private static String SECURITY_USERNAME = "SUPER";
    private static String SECURITY_PASSWORD = "5555";


    @Autowired
    private TestRestTemplate restTemplate;
    private  String baseURL = "http://localhost:8080/saltBookingManagementApp/bookingSchedule";

    @Test
    public void a_create() {

        String url = baseURL + "create/";
        System.out.println("URL: "+ url);
        System.out.println("Post data: " + bookingSchedule);
        ResponseEntity<BookingSchedule> postResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .postForEntity(url, bookingSchedule,BookingSchedule.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        bookingSchedule = postResponse.getBody();
        System.out.println("Saved data: "+ bookingSchedule);
        assertEquals(bookingSchedule.getBookingID(), postResponse.getBody().getBookingID());
    }

    @Test
    public void d_getall() {
        String url = baseURL + "all";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response);
        System.out.println(response.getBody());
    }


    @Test
    public void b_read() {
        String url = baseURL + "read/"+ bookingSchedule.getBookingID();
        System.out.println("URL "+ url);
        ResponseEntity<BookingSchedule> getResponse = restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .getForEntity(url,BookingSchedule.class);
        assertEquals(bookingSchedule.getBookingID(), getResponse.getBody().getBookingID());
        System.out.println("this is response--> "+ bookingSchedule);
    }


    @Test
    public void c_update() {
        BookingSchedule updated = new BookingSchedule.Builder().copy(bookingSchedule).setBookingDate("25-02-2020").build();
        String url = baseURL + "update/";
        System.out.println("url "+ url);
        System.out.println("Post data: "+ updated);
        bookingSchedule = updated;
        ResponseEntity<BookingSchedule> response = restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .postForEntity(url,updated,BookingSchedule.class);
        assertEquals(bookingSchedule.getBookingID(), response.getBody().getBookingID());
        System.out.println("After update: " + bookingSchedule);
    }

    @Test
    public void e_delete() {
        String url = baseURL + "/delete/" + bookingSchedule.getBookingID();
        System.out.println("URL: "+ url);
        restTemplate
                .withBasicAuth(SECURITY_USERNAME, SECURITY_PASSWORD)
                .delete(url);
        System.out.println("after delete: " +bookingSchedule);
    }
}