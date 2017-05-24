package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.container.Container;
import com.thoughtworks.ketsu.domain.container.ContainerRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ContainerMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContainerRepositoryImpl implements ContainerRepository {
    @Inject
    ContainerMapper mapper;

    @Override
    public Container save(Container container) {
        mapper.save(container);
        return container;
    }

    @Override
    public List<Container> all() {
        return mapper.all();
    }

    @Override
    public Optional<Container> ofId(String containerId) {
        return Optional.ofNullable(mapper.ofId(containerId));
    }

    @Override
    public Container update(Container container, Map<String, String> info) {
        mapper.update(container, info);
        container.setId(Integer.valueOf(info.get("id")));
        return container;
    }

    @Override
    public void delete(Container container) {
        mapper.delete(container);
    }
}
