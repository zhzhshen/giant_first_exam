package com.thoughtworks.ketsu.domain.employee;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class Employee implements Record {
    private Integer id;
    private String name;
    private Integer department_id;
    private Integer role_id;
    private String gender;

    public Employee() {
    }

    public Employee(String name, String department_id, String role_id, String gender) {
        this.name = name;
        this.department_id = Integer.valueOf(department_id);
        this.role_id = Integer.valueOf(role_id);
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", getId());
            put("name", getName());
            put("employee_url", "employees/" + getId());
            put("department_id", getDepartment_id());
            put("role_id", getRole_id());
            put("gender", getGender());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
