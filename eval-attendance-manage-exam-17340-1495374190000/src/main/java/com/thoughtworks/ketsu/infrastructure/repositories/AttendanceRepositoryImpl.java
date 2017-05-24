package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.attendance.Attendance;
import com.thoughtworks.ketsu.domain.attendance.AttendanceRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.AttendanceMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AttendanceRepositoryImpl implements AttendanceRepository {
    @Inject
    AttendanceMapper mapper;

    @Override
    public Attendance save(Attendance attendance) {
        mapper.save(attendance);
        return attendance;
    }

    @Override
    public List<Attendance> all() {
        return mapper.all();
    }

    @Override
    public Optional<Attendance> ofId(String id) {
        return Optional.ofNullable(mapper.ofId(id));
    }

    @Override
    public void delete(Attendance attendance) {
        mapper.delete(attendance);
    }

    @Override
    public Attendance update(Attendance attendance, Map info) {
        mapper.update(attendance, info);
        return attendance;
    }
}
