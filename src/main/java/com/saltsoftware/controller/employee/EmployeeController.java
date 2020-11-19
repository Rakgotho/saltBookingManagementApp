package com.saltsoftware.controller.employee;


import com.saltsoftware.entity.employee.Employee;
import com.saltsoftware.factory.employee.EmployeeFactory;
import com.saltsoftware.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/*
    Author: Abduragmaan Frank
    Student no: 217009069
    Desc: Employee Controller implementation
    Date: 26-09-2020
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
  private EmployeeService employeeService;

     @PostMapping("/create")
    public Employee create(@RequestBody Employee employee){
       // return employee;
       employee = EmployeeFactory.createEmployee(employee.getEmpName(),employee.getEmpLastName());
       return employeeService.create(employee);
    }

    // get all services
    @GetMapping("/all")
    public Set<Employee> getAll(){
       return employeeService.getAll();
    }

    // read method
    @GetMapping("/read/{id}")
    public Employee read(@PathVariable String id){ return employeeService.read(id); }
    //Update method
    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee){
       return employeeService.update(employee);
    }
    //Delete method
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){ return employeeService.delete(id);



    }

}
