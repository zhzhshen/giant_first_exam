package com.thoughtworks.ketsu.domain.container;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class Container implements Record {
    private Integer container_id;
    private Integer id;

    public Container() {
    }

    public Container(Integer id) {
        this.id = id;
    }

    public Integer getContainer_id() {
        return container_id;
    }

    public void setContainer_id(Integer container_id) {
        this.container_id = container_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", getId());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
