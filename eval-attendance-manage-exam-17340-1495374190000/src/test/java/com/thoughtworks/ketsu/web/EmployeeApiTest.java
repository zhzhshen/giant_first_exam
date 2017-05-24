package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class EmployeeApiTest extends ApiSupport {
    final String NAME = "Jane Smith";
    final Integer DEPARTMENT_ID = 2;
    final Integer UPDATE_DEPARTMENT_ID = 3;
    final Integer ROLE_ID = 3;
    final String GENDER = "male";
    final Map<String, Object> EMPLOYEE_INFO = new HashMap<String, Object>() {{
        put("name", NAME);
        put("department_id", DEPARTMENT_ID);
        put("role_id", ROLE_ID);
        put("gender", GENDER);
    }};
    final Map<String, Object> UPDATE_EMPLOYEE_INFO = new HashMap<String, Object>() {{
        put("name", NAME);
        put("department_id", UPDATE_DEPARTMENT_ID);
        put("role_id", ROLE_ID);
        put("gender", GENDER);
    }};

    @Test
    public void should_success_to_create_employee() throws Exception {
        Response response = post("/employees", EMPLOYEE_INFO);
        assertThat(response.getStatus(), is(201));
        int firstCreatedId = Integer.parseInt(response.readEntity(Map.class).get("employee_url").toString().split("/")[1]);

        response = post("/employees", EMPLOYEE_INFO);
        assertThat(response.getStatus(), is(201));
        int secondCreatedId = Integer.parseInt(response.readEntity(Map.class).get("employee_url").toString().split("/")[1]);
        assertThat(secondCreatedId, is(firstCreatedId + 1));
    }

    @Test
    public void should_success_to_view_all_employees() throws Exception {
        Response response = post("/employees", EMPLOYEE_INFO);
        int id = Integer.parseInt(response.readEntity(Map.class).get("employee_url").toString().split("/")[1]);

        response = get("/employees");
        assertThat(response.getStatus(), is(200));

        Map employeeEntity = (Map) response.readEntity(List.class).get(0);
        assertThat(employeeEntity.get("name"), is(NAME));
        assertThat(employeeEntity.get("employee_url"), is("employees/" + id));
        assertThat(employeeEntity.get("department_id"), is(DEPARTMENT_ID));
        assertThat(employeeEntity.get("role_id"), is(ROLE_ID));
        assertThat(employeeEntity.get("gender"), is(GENDER));
    }

    @Test
    public void should_404_to_view_inexist_employee() throws Exception {
        Response response = get("/employees/" + 1);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_view_an_existing_employee() throws Exception {
        Response response = post("/employees", EMPLOYEE_INFO);
        String location = response.readEntity(Map.class).get("employee_url").toString();

        response = get(location);
        Map employeeEntity = response.readEntity(Map.class);

        assertThat(response.getStatus(), is(200));
        assertThat(employeeEntity.get("name"), is(NAME));
        assertThat(employeeEntity.get("employee_url"), is(location));
        assertThat(employeeEntity.get("department_id"), is(DEPARTMENT_ID));
        assertThat(employeeEntity.get("role_id"), is(ROLE_ID));
        assertThat(employeeEntity.get("gender"), is(GENDER));
    }

    @Test
    public void should_404_to_update_inexist_employee() throws Exception {
        Response response = put("/employees/" + 1, EMPLOYEE_INFO);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_update_an_existing_employee() throws Exception {
        Response response = post("/employees", EMPLOYEE_INFO);

        String location = response.readEntity(Map.class).get("employee_url").toString();

        response = put(location, UPDATE_EMPLOYEE_INFO);
        assertThat(response.getStatus(), is(204));

        response = get(location);
        assertThat(response.getStatus(), is(200));
        Map responseEntity = response.readEntity(Map.class);
        assertThat(responseEntity.get("department_id"), is(UPDATE_DEPARTMENT_ID));
    }

    @Test
    public void should_404_to_delete_inexist_employee() throws Exception {
        Response response = delete("/employees/" + 1);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_delete_an_existing_employee() throws Exception {
        Response response = post("/employees", EMPLOYEE_INFO);

        String location = response.readEntity(Map.class).get("employee_url").toString();

        response = delete(location);
        assertThat(response.getStatus(), is(204));

        response = get(location);
        assertThat(response.getStatus(), is(404));
    }
}
