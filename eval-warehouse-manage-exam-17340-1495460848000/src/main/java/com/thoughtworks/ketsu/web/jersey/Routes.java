package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.domain.container.Container;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.warehouse.Warehouse;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI userUrl(User user) {
        return URI.create(String.format("%susers/%s", baseUri, user.getUserId().id()));
    }

    public URI containerUrl(Container container) {
        return URI.create(String.format("%scontainers/%s", baseUri, container.getContainer_id()));
    }

    public URI warehouseUrl(Warehouse warehouse) {
        return URI.create(String.format("%swareHouses/%s", baseUri, warehouse.getId()));
    }
}
