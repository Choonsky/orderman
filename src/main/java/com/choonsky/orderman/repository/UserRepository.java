package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository

public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);
    User getByEmail(String email);
}