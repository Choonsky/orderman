package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.Order;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository

public interface OrderRepository extends JpaRepository<Order, Integer>
{
}