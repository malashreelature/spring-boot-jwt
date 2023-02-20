package com.springsecurity.service;

import com.springsecurity.exception.EmployeeAlreadyExistsException;
import com.springsecurity.exception.EmployeeNotFoundException;
import com.springsecurity.model.Employee;
import com.springsecurity.model.OutputModel;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAllEmployee();

    OutputModel findById(Long id);

    OutputModel deleteById(Long id)throws EmployeeNotFoundException;

    Employee update(Employee employee, Long id);

    OutputModel save(Employee employee) throws EmployeeAlreadyExistsException;


}
