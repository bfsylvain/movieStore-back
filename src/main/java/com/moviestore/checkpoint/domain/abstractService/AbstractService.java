package com.moviestore.checkpoint.domain.abstractService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractService<T> {

    protected JpaRepository<T, Long> repository;

    public AbstractService(JpaRepository<T, Long> repository) {this.repository = repository;}

    public List<T> getAll() { return repository.findAll();}

    public T getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("entity not found")
                );
    }

    public T add(T entity) {return repository.save(entity);}

//    public abstract update(Long id, T entity);

    public void delete(Long id) {repository.deleteById(id);}
}
