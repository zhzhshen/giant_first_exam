package com.thoughtworks.ketsu.domain.warehouse;

import com.google.common.base.Strings;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse implements Record {
    private Integer id;
    private String name;
    private String containers;

    public Warehouse() {
    }

    public Warehouse(String name, String containers) {
        this.name = name;
        this.containers = containers;
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

    public String getContainers() {
        return containers;
    }

    public void setContainers(String containers) {
        this.containers = containers;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", getId());
            put("name", getName());
            if (!Strings.isNullOrEmpty(getContainers())) {
                put("containers", getContainersList());
            }
        }};
    }

    private List<Integer> getContainersList() {
        List<Integer> containersList = new ArrayList<>();
        String[] containersString = getContainers().substring(1, getContainers().length() - 1).split(",");
        for (String containerString : containersString) {
            containersList.add(Integer.valueOf(containerString.trim()));
        }
        return containersList;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
