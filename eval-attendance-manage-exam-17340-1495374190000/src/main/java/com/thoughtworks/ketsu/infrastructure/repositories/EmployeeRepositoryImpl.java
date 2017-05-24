package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.employee.Employee;
import com.thoughtworks.ketsu.domain.employee.EmployeeRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.EmployeeMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Inject
    EmployeeMapper mapper;

    @Override
    public Employee save(Employee employee) {
        mapper.save(employee);
        return employee;
    }

    @Override
    public List<Employee> all() {
        return mapper.all();
    }

    @Override
    public Optional<Employee> ofId(String id) {
        return Optional.ofNullable(mapper.ofId(id));
    }

    @Override
    public void delete(Employee warehouse) {
        mapper.delete(warehouse);
    }

    @Override
    public Employee update(Employee employee, Map info) {
        mapper.update(employee, info);
        return employee;
    }
}
