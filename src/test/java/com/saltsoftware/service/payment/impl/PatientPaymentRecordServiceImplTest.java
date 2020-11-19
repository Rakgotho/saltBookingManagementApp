package com.saltsoftware.service.payment.impl;

import com.saltsoftware.entity.payment.PatientPaymentRecord;
import com.saltsoftware.factory.payment.PatientPaymentRecordFactory;
import com.saltsoftware.service.payment.PatientPaymentRecordService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import static org.junit.Assert.*;

/* @author: Linton Appollis
/* @student number - 216182848
/* @Description - Creating Patient Payment Record Service Test Cases
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PatientPaymentRecordServiceImplTest
{
    private static PatientPaymentRecordService service;
    private static PatientPaymentRecord patientPaymentRecord = PatientPaymentRecordFactory.createPatientPaymentRecord("10001","31 Dec","R9000");

    //Select * from database table
    @Test
    public void d_getAll() {
        Set<PatientPaymentRecord> patientPaymentRecords = service.getAll();
        assertEquals(1, patientPaymentRecords.size());
        System.out.println("Overall view of all patient records: " + patientPaymentRecords);
    }

    //Create Method
    @Test
    public void a_create() {
        PatientPaymentRecord created = service.create(patientPaymentRecord);
        assertEquals(patientPaymentRecord.getPayReceiptNumber(), created.getPayReceiptNumber());
        System.out.println("Generate new patient payment record receipt number: " + created);
    }

    //Read Method
    @Test
    public void b_read() {
        PatientPaymentRecord read = service.read(patientPaymentRecord.getPayReceiptNumber());
        assertEquals(patientPaymentRecord.getPayReceiptNumber(), read.getPayReceiptNumber());
        System.out.println("Read patient payment record receipt number: " + read);
    }
    //Update Method
    @Test
    public void c_update() {
        PatientPaymentRecord updated = new PatientPaymentRecord.Builder().copy(patientPaymentRecord).setpayReceiptNumber("100023").build();
        service.update(updated);
        assertNotEquals(patientPaymentRecord.getPayReceiptNumber(), updated.getPayReceiptNumber());
        System.out.println("Update payment record with new receipt number: " + updated);
    }

    //Delete Method
    @Test
    public void e_delete() {
        service.delete(patientPaymentRecord.getPayReceiptNumber());
    }

}
