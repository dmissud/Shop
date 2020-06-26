package org.dbs.shop.common;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Abstract implementation of a bean mapper
 *
 * @param <T> The source class
 * @param <S> The target class
 */
public abstract class AbstractMapper<T, S> {

	/**
	 * @param entity entity
	 * @return the mapped dto
	 */
	public abstract T mapToDto(S entity);

	/**
	 * @param dto dto
	 * @return the mapped entity
	 */
	public abstract S mapToDomain(T dto);

	/**
	 * @param DomainList domain entity List
	 * @return a List of the mapped dto
	 */
	public List<T> mapToDtoList(final List<S> domainList) {
		return domainList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toList());
	}

	/**
	 * @param dtoList dtoList
	 * @return a List of the mapped domain entity
	 */
	public List<S> mapToDomainList(final List<T> dtoList) {
		return dtoList.stream().filter(Objects::nonNull).map(this::mapToDomain).collect(Collectors.toList());
	}

}
