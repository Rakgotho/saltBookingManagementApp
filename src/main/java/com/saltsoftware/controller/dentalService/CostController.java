package com.saltsoftware.controller.dentalService;

import com.saltsoftware.entity.dentalService.Cost;
import com.saltsoftware.entity.payment.PatientPaymentType;
import com.saltsoftware.factory.dentalService.CostFactory;
import com.saltsoftware.factory.payment.PatientPaymentTypeFactory;
import com.saltsoftware.service.dentalService.CostService;
import com.saltsoftware.service.dentalService.impl.CostServiceImpl;
import com.saltsoftware.service.payment.impl.PatientPaymentTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

/*
 *
 * @author Junade Frizlar
 * Student#: 208046402
 * Class desc: CostController
 *
 */

@RestController
@RequestMapping("/cost")
public class CostController {
    //injecting the constructor, calling cost type service
    @Autowired

    private CostServiceImpl costService;


    @PostMapping("/create")
    public Cost create(@RequestBody Cost cost){
        Cost newCost = CostFactory.createCost(cost.getCostID(), cost.getAmount());
        return costService.create(newCost);
    }

    //read
    @GetMapping("/read/{id}")
    @ResponseBody
    public Cost read(@PathVariable String id){
        return costService.read(id);
    }

    //getall
    @GetMapping("/all")
    public Set<Cost> getAll(){
        return costService.getAll();
    }

    //update
    @PostMapping("/update")
    public Cost update(@RequestBody Cost cost){
        return costService.update(cost);
    }

    //delete
    @DeleteMapping ("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable String id){
        costService.delete(id);
    }

}
