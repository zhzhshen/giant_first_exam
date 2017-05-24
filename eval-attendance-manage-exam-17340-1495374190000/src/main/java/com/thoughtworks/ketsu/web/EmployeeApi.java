package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.employee.Employee;
import com.thoughtworks.ketsu.domain.employee.EmployeeRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class EmployeeApi {
    private Employee employee;

    public EmployeeApi(Employee employee) {
        this.employee = employee;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee() {
        return employee;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateWarehouse(Map info,
                                    @Context EmployeeRepository repository,
                                    @Context Routes routes) {
        repository.update(employee, info);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteWarehouse(@Context EmployeeRepository repository) {
        repository.delete(employee);
        return Response.noContent().build();
    }
}
