package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.Order;

import com.choonsky.orderman.model.State;
import com.choonsky.orderman.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository

public interface OrderRepository extends JpaRepository<Order, Integer>
{
    public ArrayList<Order> findAllByUser(User user);
    public ArrayList<Order> findAllByState(State state);
    public ArrayList<Order> findAllByStateIn(List<State> states);
}