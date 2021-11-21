package com.techiebyte.graphql.service;

import com.techiebyte.graphql.datafetcher.AllEmployeesDataFetcher;
import com.techiebyte.graphql.datafetcher.EmployeeDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;


@Service
public class GraphQLService {

    @Value("classpath:employee.graphql")
    Resource resource;

    @Autowired
    AllEmployeesDataFetcher allEmployeesDataFetcher;

    @Autowired
    EmployeeDataFetcher employeeDataFetcher;

    private GraphQL graphQL;

    @PostConstruct
    private void loadSchema() throws IOException {

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
        .type("Query",typewiring->{
            return typewiring.dataFetcher("allEmployees",allEmployeesDataFetcher)
                    .dataFetcher("employee",employeeDataFetcher);
        })
        .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
