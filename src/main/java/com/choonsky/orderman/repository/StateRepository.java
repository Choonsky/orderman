package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.Product;
import com.choonsky.orderman.model.State;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface StateRepository extends JpaRepository<State, Integer>
{
    Optional<State> findByStateName(String stateName);
    List<State> findByIdGreaterThanEqual(Integer id);
}