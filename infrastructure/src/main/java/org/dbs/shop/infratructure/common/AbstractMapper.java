package org.dbs.shop.infratructure.common;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractMapper<D, E> {

    /**
     * @param entity entity
     * @return the mapped entity
     */
    public abstract D mapToDomain(E entity);

    /**
     * @param dto dto
     * @return the mapped entity
     */
    public abstract E mapToEntity(D dto);

    /**
     * @param entityList entityList
     * @return a List of the mapped entity
     */
    public List<D> mapToDomainList(final List<E> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::mapToDomain).collect(Collectors.toList());
    }

    /**
     * @param dtoList dtoList
     * @return a List of the mapped entity
     */
    public List<E> mapToEntityList(final List<D> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toList());
    }

}