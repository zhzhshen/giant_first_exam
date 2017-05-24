package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.domain.attendance.Attendance;
import com.thoughtworks.ketsu.domain.employee.Employee;
import com.thoughtworks.ketsu.domain.user.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI userUrl(User user) {
        return URI.create(String.format("%susers/%s", baseUri, user.getUserId().id()));
    }

    public URI employeeUrl(Employee employee) {
        return URI.create(String.format("%semployees/%s", baseUri, employee.getId()));
    }

    public URI attendanceUrl(Attendance attendance) {
        return URI.create(String.format("%sattendances/%s", baseUri, attendance.getId()));
    }
}
