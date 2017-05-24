package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.warehouse.Warehouse;
import com.thoughtworks.ketsu.domain.warehouse.WarehouseRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("wareHouses")
public class WarehousesApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createWarehouse(Map<String, String> info,
                               @Context WarehouseRepository warehouseRepository,
                               @Context Routes routes) {
        Warehouse warehouse = new Warehouse(info.get("name"), null);
        warehouse = warehouseRepository.save(warehouse);
        Map<String, String> warehouseUrl = new HashMap<>();
        warehouseUrl.put("warehouse_url", routes.warehouseUrl(warehouse).getPath().substring(1));
        return Response.created(routes.warehouseUrl(warehouse)).entity(warehouseUrl).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@Context WarehouseRepository warehouseRepository) {
        List<Warehouse> all = warehouseRepository.all();
        Map<String, Object> entity = new HashMap<>();
        entity.put("totalCount", all.size());
        entity.put("providers", all);
        return Response.ok().entity(entity).build();
    }

    @Path("{warehouseId}")
    public WarehouseApi getUser(@PathParam("warehouseId") String warehouseId,
                                @Context WarehouseRepository repository) {
        return repository.ofId(warehouseId)
                .map(WarehouseApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
