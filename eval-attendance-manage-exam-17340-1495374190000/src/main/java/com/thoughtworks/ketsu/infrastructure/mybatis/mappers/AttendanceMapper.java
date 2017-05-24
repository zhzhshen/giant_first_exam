package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.attendance.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AttendanceMapper {
    void save(@Param("attendance") Attendance attendance);

    List<Attendance> all();

    Attendance ofId(@Param("id") String id);

    void delete(@Param("attendance") Attendance attendance);

    void update(@Param("attendance") Attendance attendance, @Param("info") Map<String, String> info);
}
