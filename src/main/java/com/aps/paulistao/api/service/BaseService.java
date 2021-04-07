package com.aps.paulistao.api.service;

import com.aps.paulistao.api.entity.BaseEntity;
import com.aps.paulistao.api.exception.NotFoundException;
import com.aps.paulistao.api.repository.BaseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public abstract class BaseService<T extends BaseEntity> {
    @Autowired
    private BaseRepository<T> repository;
    private Class<T> entityClass;

    public BaseService(final Class<T> entityClass){
        this.entityClass = entityClass;
    }

    private T save(final T oldEntity, final T newEntity, final String...ignoreProps) {
        BeanUtils.copyProperties(oldEntity, newEntity, ignoreProps);
        return repository.save(newEntity);
    }

    @Transactional
    public T save(final T entity) {
        T entityObj = BeanUtils.instantiateClass(entityClass);
        return save(entity, entityObj, "id");
    }

    @Transactional
    public T update(final Long id, final T updatedEntity) {
        final T entity = findById(id);
        return save(updatedEntity, entity, "id");
    }

    @Transactional
    public void delete(final Long id) {
        final T entity = findById(id);
        repository.deleteById(entity.getId());
    }

    public T findById(final Long id) {
        final Optional<T> opEntity = repository.findById(id);

        if(opEntity.isPresent()) {
            return opEntity.get();
        }

        throw new NotFoundException("final ");
    }

    public List<T> find(){
        return repository.findAll();
    }

    //public abstract void validate();
}