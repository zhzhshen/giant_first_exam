package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.container.Container;
import com.thoughtworks.ketsu.domain.container.ContainerRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class ContainerApi {
    private Container container;

    public ContainerApi(Container container) {
        this.container = container;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Container getContainer() {
        return container;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateContainer(Map<String, String> info,
                                    @Context ContainerRepository repository,
                                    @Context Routes routes) {
        repository.update(container, info);
        return Response.noContent().location(routes.containerUrl(container)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteContainer(@Context ContainerRepository repository) {
        repository.delete(container);
        return Response.noContent().build();
    }
}
