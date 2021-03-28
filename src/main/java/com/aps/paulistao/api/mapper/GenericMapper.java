package com.aps.paulistao.api.mapper;

public interface GenericMapper<T, D> {
    D toDTO(final T entity);
    T toEntity(final D dto);
}