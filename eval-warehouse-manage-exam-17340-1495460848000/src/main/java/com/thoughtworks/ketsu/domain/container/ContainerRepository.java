package com.thoughtworks.ketsu.domain.container;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContainerRepository {
    Container save(Container container);

    List<Container> all();

    Optional<Container> ofId(String containerId);

    Container update(Container container, Map<String, String> info);

    void delete(Container container);
}
