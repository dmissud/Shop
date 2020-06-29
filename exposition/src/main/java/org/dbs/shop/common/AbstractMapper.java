package org.dbs.shop.common;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractMapper<D, E> {
    /**
     * @param entity entity
     * @return the mapped dto
     */
    public abstract D mapToDto(E entity);

    /**
     * @param dto dto
     * @return the mapped entity
     */
    public abstract E mapToDomain(D dto);

    /**
     * @param domainList domain entity List
     * @return a List of the mapped dto
     */
    public List<D> mapToDtoList(final List<E> domainList) {
        return domainList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * @param dtoList dtoList
     * @return a List of the mapped domain entity
     */
    public List<E> mapToDomainList(final List<D> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::mapToDomain).collect(Collectors.toList());
    }
}
