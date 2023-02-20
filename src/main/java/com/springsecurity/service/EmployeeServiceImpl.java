package com.springsecurity.service;

import com.springsecurity.exception.EmployeeAlreadyExistsException;
import com.springsecurity.exception.EmployeeNotFoundException;
import com.springsecurity.model.Employee;
import com.springsecurity.model.OutputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;



    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }
    @Override
    public Employee update(Employee employee, Long id) {
        Employee emp = this.employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" not found"));
        emp.setName(employee.getName());
        emp. setEmail(employee.getEmail());
        this.employeeRepository.save(emp);
        Employee save = this.employeeRepository.save(emp);
        return save;

    }


    @Override
    public OutputModel save(Employee employee) throws EmployeeAlreadyExistsException {
        OutputModel model = new OutputModel();
        try {
            model.setEmployee((Employee) employeeRepository.save(employee));
            if (model != null) {
                employeeRepository.save(employee);

            } else {
                throw new EmployeeAlreadyExistsException(
                        "employee already exists!!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new EmployeeAlreadyExistsException("employee already exists!!");
        } catch (Exception e) {
            model.setEmployee(null);
            model.setErrorMessage(e.getMessage());
        }


        return model;
    }




    @Override
    public OutputModel findById(Long id) {
        OutputModel model = new OutputModel();
        try {
            model.setEmployee((Employee) employeeRepository.findById(id).orElseThrow(() ->
                    new EmployeeNotFoundException("Employee with id not found")));
            model.setErrorMessage("");
            if (!model.getEmployee().getId().equals(id)) {
                throw new EmployeeNotFoundException("");
            }
        } catch (EmployeeNotFoundException e) {
            model.setEmployee(null);
            model.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            model.setEmployee(null);
            model.setErrorMessage(e.getMessage());
        }
        return model;
    }

    @Override
    public OutputModel deleteById(Long id) throws EmployeeNotFoundException{
        OutputModel model = new OutputModel();
        try {
            model.setEmployee((Employee) employeeRepository.findById(id).orElseThrow(() ->
                    new EmployeeNotFoundException("Employee with id not found")));
            model.setErrorMessage("");
            if (!model.getEmployee().getId().equals(id)) {
                throw new EmployeeNotFoundException("");
            }
        } catch (EmployeeNotFoundException e) {
            model.setEmployee(null);
            model.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            model.setEmployee(null);
            model.setErrorMessage(e.getMessage());
        }
        return model;

    }



}