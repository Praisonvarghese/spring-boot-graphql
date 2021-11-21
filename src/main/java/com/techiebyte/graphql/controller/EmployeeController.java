package com.techiebyte.graphql.controller;

import com.techiebyte.graphql.dao.EmployeeRespository;
import com.techiebyte.graphql.entity.Employee;
import com.techiebyte.graphql.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRespository employeeRespository;

    @Autowired
    private GraphQLService graphQLService;


    @PostMapping("/api/addEmployee")
    public String addEmployee(@RequestBody List<Employee> employee) {
        employeeRespository.saveAll(employee);
        return "Employee Added Successfully";
    }

    @GetMapping("/api/getEmployees")
    public List<Employee> getEmployee() {
        return (List<Employee>) employeeRespository.findAll();
    }

    @PostMapping("/graphql/getEmployees")
    public ResponseEntity<Object> getGraphqlQLemployees(@RequestBody String query){
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<Object>(execute,HttpStatus.OK);
    }

}
