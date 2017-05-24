package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.warehouse.Warehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarehouseMapper {
    void save(@Param("warehouse") Warehouse warehouse);

    List<Warehouse> all();

    Warehouse ofId(@Param("id") String id);

    void delete(@Param("warehouse") Warehouse warehouse);

    void update(@Param("warehouse") Warehouse warehouse, @Param("info") Map<String, String> info);
}
