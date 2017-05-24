package com.thoughtworks.ketsu.domain.attendance;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AttendanceRepository {
    Attendance save(Attendance attendance);

    List<Attendance> all();

    Optional<Attendance> ofId(String id);

    void delete(Attendance attendance);

    Attendance update(Attendance attendance, Map info);
}
