package com.saltsoftware.factory.dentalService;

import com.saltsoftware.entity.dentalService.ServiceCost;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceCostFactoryTest {


    public static void beforeClass() throws Exception {
        ServiceCost svc = ServiceCostFactory.buildServiceCost("GUM_CLEAN_4100", "VVIP_4100");
    }

    @Test
    public void getServiceCost(){

        ServiceCost servCost = new ServiceCost.Builder()
                .costId("VVIP_4100")
                .serviceId("GUM_CLEAN_4100")
                .build();

        assertEquals("VVIP_4100" + "GUM_CLEAN_4100", servCost.getCostId() + servCost.getServiceId());
        System.out.println(servCost.toString());
    }

    @Test
    public void variousObjects() {
        //Testing object are not the same
        ServiceCost serviceCost1 = new ServiceCost.Builder()
                .costId("VVIP_4100")
                .serviceId("GUM_CLEAN_4100")
                .build();

        ServiceCost serviceCost2 = new ServiceCost.Builder()
                .costId("VVIP_4100")
                .serviceId("GUM_CLEAN_4100")
                .build();

        Assert.assertNotSame(serviceCost1, serviceCost2.toString());
        System.out.println(serviceCost1);
        System.out.println(serviceCost2);

    }

}
