package com.techiebyte.graphql.dao;

import com.techiebyte.graphql.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRespository extends CrudRepository<Employee,String> {

    Optional<Employee> findByName(String name);
}
