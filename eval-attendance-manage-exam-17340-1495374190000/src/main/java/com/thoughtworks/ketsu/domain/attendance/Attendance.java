package com.thoughtworks.ketsu.domain.attendance;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class Attendance implements Record {
    private Integer id;
    private Integer employee_id;
    private String description;
    private String from_date;
    private String to_date;
    private Boolean present;

    public Attendance() {
    }

    public Attendance(String employee_id, String description, String from_date, String to_date, String present) {
        this.employee_id = Integer.valueOf(employee_id);
        this.description = description;
        this.from_date = from_date;
        this.to_date = to_date;
        this.present = Boolean.valueOf(present);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", getId());
            put("employee_id", getEmployee_id());
            put("description", getDescription());
            put("from_date", getFrom_date());
            put("to_date", getTo_date());
            put("present", getPresent());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
