package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.warehouse.Warehouse;
import com.thoughtworks.ketsu.domain.warehouse.WarehouseRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.WarehouseMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WarehouseRepositoryImpl implements WarehouseRepository {
    @Inject
    WarehouseMapper mapper;

    @Override
    public Warehouse save(Warehouse warehouse) {
        mapper.save(warehouse);
        return warehouse;
    }

    @Override
    public List<Warehouse> all() {
        return mapper.all();
    }

    @Override
    public Optional<Warehouse> ofId(String warehouseId) {
        return Optional.ofNullable(mapper.ofId(warehouseId));
    }

    @Override
    public void delete(Warehouse warehouse) {
        mapper.delete(warehouse);
    }

    @Override
    public Warehouse update(Warehouse warehouse, Map info) {
        info.put("containers", info.get("containers").toString());
        mapper.update(warehouse, info);
        return warehouse;
    }
}
