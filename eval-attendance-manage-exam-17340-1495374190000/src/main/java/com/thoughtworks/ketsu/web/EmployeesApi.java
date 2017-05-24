package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.employee.Employee;
import com.thoughtworks.ketsu.domain.employee.EmployeeRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("employees")
public class EmployeesApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(Map<String, String> info,
                                    @Context EmployeeRepository repository,
                                    @Context Routes routes) {
        Employee employee = new Employee(info.get("name"),
                info.get("department_id"),
                info.get("role_id"),
                info.get("gender"));
        employee = repository.save(employee);
        Map<String, String> employeeUrl = new HashMap<>();
        employeeUrl.put("employee_url", routes.employeeUrl(employee).getPath().substring(1));
        return Response.created(routes.employeeUrl(employee)).entity(employeeUrl).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> all(@Context EmployeeRepository repository) {
        return repository.all();
    }

    @Path("{employeeId}")
    public EmployeeApi getEmployee(@PathParam("employeeId") String employeeId,
                                   @Context EmployeeRepository repository) {
        return repository.ofId(employeeId)
                .map(EmployeeApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
