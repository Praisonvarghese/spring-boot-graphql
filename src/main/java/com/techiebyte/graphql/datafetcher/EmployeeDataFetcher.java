package com.techiebyte.graphql.datafetcher;

import com.techiebyte.graphql.dao.EmployeeRespository;
import com.techiebyte.graphql.entity.Employee;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeDataFetcher implements DataFetcher<Employee> {
    @Autowired
    EmployeeRespository  employeeRespository;

    @Override
    public Employee get(DataFetchingEnvironment dataFetchingEnvironment) {
        return employeeRespository.findByName(dataFetchingEnvironment.getArgument("id")).get();
    }
}
