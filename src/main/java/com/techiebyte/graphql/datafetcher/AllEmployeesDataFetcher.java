package com.techiebyte.graphql.datafetcher;

import com.techiebyte.graphql.dao.EmployeeRespository;
import com.techiebyte.graphql.entity.Employee;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class AllEmployeesDataFetcher implements DataFetcher<List<Employee>> {

    @Autowired
    EmployeeRespository employeeRespository;

    @Override
    public List<Employee> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return (List<Employee>) employeeRespository.findAll();
    }
}
