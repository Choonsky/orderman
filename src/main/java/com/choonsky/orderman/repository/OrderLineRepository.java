package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.OrderLine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer>
{
}