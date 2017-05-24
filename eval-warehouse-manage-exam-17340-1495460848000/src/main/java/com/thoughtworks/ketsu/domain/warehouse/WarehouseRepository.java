package com.thoughtworks.ketsu.domain.warehouse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WarehouseRepository {
    Warehouse save(Warehouse warehouse);

    List<Warehouse> all();

    Optional<Warehouse> ofId(String warehouseId);

    void delete(Warehouse warehouse);

    Warehouse update(Warehouse warehouse, Map info);
}
