package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class AttendanceApiTest extends ApiSupport {
    final String DESCRIPTION = "sick leave";
    final String UPDATE_DESCRIPTION = "annual leave";
    final Integer EMPLOYEE_ID = 1;
    final String FROM_DATE = new Date().toString();
    final String TO_DATE = new Date().toString();
    final boolean PRESENT = false;
    final Map<String, Object> ATTENDANCE_INFO = new HashMap<String, Object>() {{
        put("employee_id", EMPLOYEE_ID);
        put("description", DESCRIPTION);
        put("from_date", FROM_DATE);
        put("to_date", TO_DATE);
        put("present", PRESENT);
    }};
    final Map<String,Object> UPDATE_ATTENDANCE_INFO = new HashMap<String, Object>() {{
        put("employee_id", EMPLOYEE_ID);
        put("description", UPDATE_DESCRIPTION);
        put("from_date", FROM_DATE);
        put("to_date", TO_DATE);
        put("present", PRESENT);
    }};

    @Test
    public void should_success_to_create_attendance() throws Exception {
        Response response = post("/attendances", ATTENDANCE_INFO);
        assertThat(response.getStatus(), is(201));
        int firstCreatedId = Integer.parseInt(response.readEntity(Map.class).get("attendance_url").toString().split("/")[1]);

        response = post("/attendances", ATTENDANCE_INFO);
        assertThat(response.getStatus(), is(201));
        int secondCreatedId = Integer.parseInt(response.readEntity(Map.class).get("attendance_url").toString().split("/")[1]);
        assertThat(secondCreatedId, is(firstCreatedId + 1));
    }

    @Test
    public void should_success_to_view_all_attendance() throws Exception {
        Response response = post("/attendances", ATTENDANCE_INFO);
        int id = Integer.parseInt(response.readEntity(Map.class).get("attendance_url").toString().split("/")[1]);

        response = get("/attendances");
        assertThat(response.getStatus(), is(200));

        Map employeeEntity = (Map) response.readEntity(List.class).get(0);
        assertThat(employeeEntity.get("id"), is(id));
        assertThat(employeeEntity.get("employee_id"), is(EMPLOYEE_ID));
        assertThat(employeeEntity.get("description"), is(DESCRIPTION));
        assertThat(employeeEntity.get("from_date"), is(FROM_DATE));
        assertThat(employeeEntity.get("to_date"), is(TO_DATE));
        assertThat(employeeEntity.get("present"), is(PRESENT));
    }

    @Test
    public void should_404_to_view_inexist_attendance() throws Exception {
        Response response = get("/attendances/" + 0);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_view_an_existing_attendance() throws Exception {
        Response response = post("/attendances", ATTENDANCE_INFO);
        String location = response.readEntity(Map.class).get("attendance_url").toString();

        response = get(location);
        Map attendanceEntity = response.readEntity(Map.class);

        assertThat(response.getStatus(), is(200));
        assertThat(attendanceEntity.get("employee_id"), is(EMPLOYEE_ID));
        assertThat(attendanceEntity.get("description"), is(DESCRIPTION));
        assertThat(attendanceEntity.get("from_date"), is(FROM_DATE));
        assertThat(attendanceEntity.get("to_date"), is(TO_DATE));
        assertThat(attendanceEntity.get("present"), is(PRESENT));
    }

    @Test
    public void should_404_to_update_inexist_attendance() throws Exception {
        Response response = put("/attendances/" + 1, UPDATE_ATTENDANCE_INFO);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_update_an_existing_attendance() throws Exception {
        Response response = post("/attendances", ATTENDANCE_INFO);

        String location = response.readEntity(Map.class).get("attendance_url").toString();

        response = put(location, UPDATE_ATTENDANCE_INFO);
        assertThat(response.getStatus(), is(204));

        response = get(location);
        assertThat(response.getStatus(), is(200));
        Map responseEntity = response.readEntity(Map.class);
        assertThat(responseEntity.get("description"), is(UPDATE_DESCRIPTION));
    }

    @Test
    public void should_404_to_delete_inexist_attendance() throws Exception {
        Response response = delete("/attendances/" + 1);
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_success_to_delete_an_existing_attendance() throws Exception {
        Response response = post("/attendances", ATTENDANCE_INFO);

        String location = response.readEntity(Map.class).get("attendance_url").toString();

        response = delete(location);
        assertThat(response.getStatus(), is(204));

        response = get(location);
        assertThat(response.getStatus(), is(404));
    }
}
