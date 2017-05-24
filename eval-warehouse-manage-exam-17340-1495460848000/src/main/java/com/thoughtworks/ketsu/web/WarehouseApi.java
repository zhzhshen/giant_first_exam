package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.warehouse.Warehouse;
import com.thoughtworks.ketsu.domain.warehouse.WarehouseRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class WarehouseApi {
    private Warehouse warehouse;

    public WarehouseApi(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Warehouse getWarehouse() {
        return warehouse;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateWarehouse(Map info,
                                    @Context WarehouseRepository repository,
                                    @Context Routes routes) {
        repository.update(warehouse, info);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteWarehouse(@Context WarehouseRepository repository) {
        repository.delete(warehouse);
        return Response.noContent().build();
    }
}
