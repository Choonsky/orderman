package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.Action;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Integer>
{
    long count();
    boolean existsById(Integer id);
    void delete(Action action);
    Action save(Action action); // return saved Action
    Optional<Action> findById(Integer id);

}