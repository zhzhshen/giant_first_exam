package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.container.Container;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ContainerMapper {
    void save(@Param("container") Container container);

    List<Container> all();

    Container ofId(@Param("container_id") String container_id);

    void update(@Param("container") Container container, @Param("info") Map<String, String> info);

    void delete(@Param("container") Container container);
}
