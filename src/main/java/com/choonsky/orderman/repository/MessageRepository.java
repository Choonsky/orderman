package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.Message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer>
{
}