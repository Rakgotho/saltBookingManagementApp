package com.saltsoftware.controller.payment;

import com.saltsoftware.entity.employee.Employee;
import com.saltsoftware.factory.employee.EmployeeFactory;
import com.saltsoftware.service.payment.*;
import com.saltsoftware.entity.payment.PatientPaymentRecord;
import com.saltsoftware.factory.payment.PatientPaymentRecordFactory;
import com.saltsoftware.service.payment.PatientPaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
/*
    Author: Linton Appollis
    Student no: 216182484
    Desc: Patient Payment Record Controller
    Date: 27 Sept
 */
@RestController
@RequestMapping("/paymentrecord")
public class PatientPaymentRecordController
{
    //used to create a connection to the service package
    @Autowired
    private PatientPaymentRecordService patientPaymentRecordService;

    @PostMapping("/create")
    public PatientPaymentRecord create(@RequestBody PatientPaymentRecord patientPaymentRecord){
        patientPaymentRecord = PatientPaymentRecordFactory.createPatientPaymentRecord(patientPaymentRecord.getPayReceiptNumber(),patientPaymentRecord.getPayDate(),patientPaymentRecord.getPayAmount());
        return patientPaymentRecordService.create(patientPaymentRecord);
    }

    // get all services
    @GetMapping("/all")
    public Set<PatientPaymentRecord> getAll(){
        return patientPaymentRecordService.getAll();
    }

    // read method
    @GetMapping("/read/{id}")
    public PatientPaymentRecord read(@PathVariable String id){ return patientPaymentRecordService.read(id); }

    //Update method
    @PutMapping("/update")
    public PatientPaymentRecord update(@RequestBody PatientPaymentRecord patientPaymentRecord){
        return patientPaymentRecordService.update(patientPaymentRecord);
    }
    //Delete method
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){ return patientPaymentRecordService.delete(id);}


}


