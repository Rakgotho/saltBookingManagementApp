package com.saltsoftware.service.dentalService.impl;

import com.saltsoftware.entity.dentalService.ServiceCost;
import com.saltsoftware.repository.dentalService.ServiceCostRepository;
import com.saltsoftware.service.dentalService.ServiceCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/*
        Author: Lebusa Letsoha
        Description: The class implements interface ServiceCostService interface to achieve the highest level of abstraction
        Date: 07-Sep-2020
 */
@Service
public class ServiceCostServiceImpl implements ServiceCostService {


    @Autowired
    private ServiceCostRepository repository;

    @Override
    public Set<ServiceCost> getAll() {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public ServiceCost create(ServiceCost serviceCost) {
        return this.repository.save(serviceCost);
    }

    @Override
    public ServiceCost read(String s) {
        return this.repository.findById(s).orElseGet(null);
    }

    @Override
    public ServiceCost update(ServiceCost serviceCost) {
        if (this.repository.existsById(serviceCost.getCostId()))
        {
            return this.repository.save(serviceCost);}
        return null;
    }

    @Override
    public boolean delete(String s) {
        this.repository.deleteById(s);
        if(this.repository.existsById(s)) return false;
        else return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCostServiceImpl that = (ServiceCostServiceImpl) o;
        return repository.equals(that.repository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository);
    }
}
