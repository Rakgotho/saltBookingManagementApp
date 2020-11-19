package com.saltsoftware.controller.payment;


import com.saltsoftware.entity.payment.PatientPaymentType;
import com.saltsoftware.factory.payment.PatientPaymentTypeFactory;
import com.saltsoftware.service.payment.impl.PatientPaymentTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/*
 * Created by: Heinrich Arends
 * Student no: 217166792
 * Desc: Create Controller to expose PatientPaymentType Service
 **/

import java.util.Set;

@RestController
@RequestMapping("/paymenttype")
public class PatientPaymentTypeController {

    //injecting the constructor, calling payment type service
    @Autowired
    private PatientPaymentTypeServiceImpl paymentTypeService;

    @PostMapping("/create")
    public PatientPaymentType create(@RequestBody PatientPaymentType patientPaymentType){
        PatientPaymentType newPaymentType = PatientPaymentTypeFactory.createPaymentType(patientPaymentType.getPaymentDescrip());
        return paymentTypeService.create(newPaymentType);
    }

    //read
    @GetMapping("/read/{id}")
    @ResponseBody
    public PatientPaymentType read(@PathVariable String id){
        return paymentTypeService.read(id);
    }

    @GetMapping("/all") //send to the server
    public Set<PatientPaymentType> getall(){
        return paymentTypeService.getAll();
    }

    //update
    @PostMapping("/update")
    public PatientPaymentType update(@RequestBody PatientPaymentType patientPaymentType){
        return paymentTypeService.update(patientPaymentType);
    }

    //delete
    @DeleteMapping ("/delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable String id){
        return paymentTypeService.delete(id);
    }
}
