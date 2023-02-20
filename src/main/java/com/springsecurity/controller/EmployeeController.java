package com.springsecurity.controller;

import com.springsecurity.exception.EmployeeAlreadyExistsException;
import com.springsecurity.model.Employee;
import com.springsecurity.model.OutputModel;
import com.springsecurity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployee();
    }

    @PutMapping("/emp{id}")
    public  ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable long id){
        Employee emp = this.employeeService.update(employee, id);
        return new ResponseEntity<>(emp ,HttpStatus.CREATED);
    }


    @PostMapping(value = "/employee")
    public ResponseEntity<OutputModel> CreateEmployee(@RequestBody Employee employee) {
        OutputModel model = new OutputModel();
        try {
            if (model != null) {
                model = (OutputModel) employeeService.save(employee);
                return new ResponseEntity<>(model, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
            }
        } catch (EmployeeAlreadyExistsException e) {
            model.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(model, HttpStatus.valueOf(500));
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OutputModel> deleteEmployee(@PathVariable("id") Long id) {
        OutputModel outputModel1 = new OutputModel();
        try {
            outputModel1 = (OutputModel) this.employeeService.deleteById(id);
            if (outputModel1.getErrorMessage().equals("")) {
                return new ResponseEntity<>(outputModel1, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(outputModel1, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(outputModel1, HttpStatus.valueOf(500));
        }

    }

    @GetMapping("/employee{id}")
    public ResponseEntity<OutputModel> getById(@PathVariable Long id) {
        OutputModel outputModel = new OutputModel();
        try {
            outputModel = this.employeeService.findById(id);
            if (outputModel.getErrorMessage().equals("")) {
                return new ResponseEntity<>(outputModel, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(outputModel, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(outputModel, HttpStatus.valueOf(500));
        }
    }
}



