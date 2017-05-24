package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.employee.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    void save(@Param("employee") Employee employee);

    List<Employee> all();

    Employee ofId(@Param("id") String id);

    void delete(@Param("employee") Employee employee);

    void update(@Param("employee") Employee employee, @Param("info") Map<String, String> info);
}
