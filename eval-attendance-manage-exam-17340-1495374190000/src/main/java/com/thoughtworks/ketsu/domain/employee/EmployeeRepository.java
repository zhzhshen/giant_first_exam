package com.thoughtworks.ketsu.domain.employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);

    List<Employee> all();

    Optional<Employee> ofId(String id);

    void delete(Employee employee);

    Employee update(Employee employee, Map info);
}
