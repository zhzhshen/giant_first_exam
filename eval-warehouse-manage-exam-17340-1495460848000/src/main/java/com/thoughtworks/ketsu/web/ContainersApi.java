package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.container.Container;
import com.thoughtworks.ketsu.domain.container.ContainerRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("containers")
public class ContainersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createContainer(Map<String, String> info,
                               @Context ContainerRepository repository,
                               @Context Routes routes) {
        Container container = new Container(Integer.valueOf(info.get("id")));
        container = repository.save(container);
        Map<String, String> containerURL = new HashMap<>();
        containerURL.put("container_url", routes.containerUrl(container).getPath().substring(1));
        return Response.created(routes.containerUrl(container)).entity(containerURL).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@Context ContainerRepository repository) {
        List<Container> all = repository.all();
        Map<String, Object> entity = new HashMap<>();
        entity.put("totalCount", all.size());
        entity.put("containers", all);
        return Response.ok().entity(entity).build();
    }

    @Path("{containerId}")
    public ContainerApi getContainer(@PathParam("containerId") String containerId,
                           @Context ContainerRepository repository) {
        return repository.ofId(containerId)
                .map(ContainerApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
