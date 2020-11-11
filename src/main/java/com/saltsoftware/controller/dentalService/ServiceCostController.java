package com.saltsoftware.controller.dentalService;


import com.saltsoftware.entity.dentalService.Cost;
import com.saltsoftware.entity.dentalService.Service;
import com.saltsoftware.entity.dentalService.ServiceCost;
import com.saltsoftware.factory.dentalService.ServiceCostFactory;
import com.saltsoftware.service.dentalService.impl.CostServiceImpl;
import com.saltsoftware.service.dentalService.impl.ServiceCostServiceImpl;
import com.saltsoftware.service.dentalService.impl.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.*;

/*
 *
 * @author Lebusa.Letsoha
 * Student#: 216059186
 * Class desc: CostController: created for mapping the ServiceCostService, "CRUD" logic of the application to the user and enables the application to be accessable via the web
 * Date: 27 Sep 2020
 */

import java.util.Set;

@RestController
@RequestMapping("/servicecost")
public class ServiceCostController {
    @MockBean
    private ServiceCostServiceImpl serviceCostService;

    //This handles everyt related serviceCost operations
    @Autowired
    private ServiceCostServiceImpl service;

    @Autowired
    private ServiceServiceImpl serviceService;

    @Autowired
    private CostServiceImpl costService;

    @PostMapping("/create")
    public ServiceCost create(@RequestBody ServiceCost serviceCost){
        boolean costExist = false;
        boolean serviceExist = false;
        System.out.println(serviceCost.getCostId());

        Cost cost = costService.read(serviceCost.getCostId());
        if (cost != null){
            costExist = true;
        }
        Service service = serviceService.read(serviceCost.getServiceId());
        if (service != null)
        {
            serviceExist = true;
        }

        if (costExist && serviceExist)
            return serviceCostService.create(serviceCost);
        else return ServiceCostFactory.buildServiceCost("", "");
    }

    //read
    @GetMapping("/read/{id}")
    @ResponseBody
    public ServiceCost read(@PathVariable String id){
        return serviceCostService.read(id);
    }

    //getall
    @GetMapping("/all")
    public Set<ServiceCost> getall(){
        return serviceCostService.getAll();
    }

    //update
    @PostMapping("/update")
    public ServiceCost update(@RequestBody ServiceCost serviceCost){
        return serviceCostService.update(serviceCost);
    }

    //delete
    @DeleteMapping ("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable String id){
        serviceCostService.delete(id);
    }

}
