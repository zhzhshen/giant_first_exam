package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendance.Attendance;
import com.thoughtworks.ketsu.domain.attendance.AttendanceRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class AttendanceApi {
    private Attendance attendance;

    public AttendanceApi(Attendance attendance) {
        this.attendance = attendance;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Attendance getAttendance() {
        return attendance;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateWarehouse(Map info,
                                    @Context AttendanceRepository repository,
                                    @Context Routes routes) {
        repository.update(attendance, info);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteWarehouse(@Context AttendanceRepository repository) {
        repository.delete(attendance);
        return Response.noContent().build();
    }
}
