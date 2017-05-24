package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendance.Attendance;
import com.thoughtworks.ketsu.domain.attendance.AttendanceRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("attendances")
public class AttendancesApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAttendance(Map<String, String> info,
                                    @Context AttendanceRepository repository,
                                    @Context Routes routes) {
        Attendance attendance = new Attendance(info.get("employee_id"),
                info.get("description"),
                info.get("from_date"),
                info.get("to_date"),
                info.get("present"));
        attendance = repository.save(attendance);
        Map<String, String> attendanceURL = new HashMap<>();
        attendanceURL.put("attendance_url", routes.attendanceUrl(attendance).getPath().substring(1));
        return Response.created(routes.attendanceUrl(attendance)).entity(attendanceURL).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Attendance> all(@Context AttendanceRepository repository) {
        return repository.all();
    }

    @Path("{attendanceId}")
    public AttendanceApi getUser(@PathParam("attendanceId") String id,
                           @Context AttendanceRepository repository) {
        return repository.ofId(id)
                .map(AttendanceApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
