package com.saltsoftware.service.dentalService;//package com.saltsoftware.service.dentalService.impl;

import com.saltsoftware.entity.dentalService.ServiceCost;
import com.saltsoftware.factory.dentalService.ServiceCostFactory;
import com.saltsoftware.service.dentalService.ServiceCostService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;



  /*     Created by: Lebusa Letsoha
        Description: This is a test class for the ServiceCostServiceImpl class
        Date: 07-Sep-2020
        Stud#: 216059186
*/


import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ServiceCostServiceImplTest {

    @Autowired
    private static ServiceCostService service;
    private static ServiceCost serviceCost = ServiceCostFactory.buildServiceCost("VIP_01", "0102");

    @Test
    public void a_create() {
        ServiceCost created = service.create(serviceCost);
        Assert.assertNotEquals(serviceCost.getServiceId(), created.getServiceId());
        System.out.println("Created : " + created);
    }

    @Test
    public void b_read() {
        ServiceCost read = service.read(serviceCost.getServiceId());
        System.out.println("Read :" + read);
    }

    @Test
    public void c_update() {
        ServiceCost updated = new ServiceCost.Builder().copy(serviceCost).serviceId("VIP_01").build();
        updated = service.update(updated);
        System.out.println("Record updated:"+ updated);
    }
    @Test
    public void e_delete() {
        service.delete(serviceCost.getServiceId());
    }

    @Test
    public void d_getAll() {
        Set<ServiceCost> serviceCosts = service.getAll();
        assertEquals(1, serviceCosts.size());
        System.out.println(service.getAll());
    }
}
