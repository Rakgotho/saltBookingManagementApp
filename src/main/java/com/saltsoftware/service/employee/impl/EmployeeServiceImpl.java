package com.saltsoftware.service.employee.impl;

import com.saltsoftware.entity.employee.Employee;
import com.saltsoftware.repository.employee.EmployeeRepository;
import com.saltsoftware.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/* Author: Abduragmaan Frank
   Student no: 217009069
   Desc: Employee Service implementation
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static EmployeeService service = null;
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Set<Employee> getAll() {

        return this.repository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Employee create(Employee employee) {

        return this.repository.save(employee);
    }

    @Override
    public Employee read(String s) {
        return this.repository.findById(s).orElseGet(null);
    }

    @Override
    public Employee update(Employee employee) {
        if (this.repository.existsById(employee.getEmpId())) {
            return this.repository.save(employee);
        }
        return null;
    }

    @Override
    public boolean delete(String s) {

         repository.deleteById(s);
         if(this.repository.existsById(s)){
             return false;
        }
         else  {
            return true;
        }
    }
}
