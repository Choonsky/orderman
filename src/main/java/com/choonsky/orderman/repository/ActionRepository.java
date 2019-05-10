package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.Action;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



/*

a. We can start our query method names with find...By, read...By, query...By, count...By, and get...By.
Before By we can add expression such as Distinct . After By we need to add property names of our entity.

b. To get data on the basis of more than one property we can concatenate property names using And and Or while creating method names.

c. If we want to use completely custom name for our method, we can use @Query annotation to write query.

This will be AUTO IMPLEMENTED by Spring into a Bean called actionRepository
JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository
 */

public interface ActionRepository extends JpaRepository<Action, Integer>
{
    long count();
    boolean existsById(Integer id);
    void delete(Action action);
    Action save(Action action); // return saved Action
    Optional<Action> findById(Integer id);
    Optional<Action> findByIdOrder(Integer idOrder);

//    Optional<Action> getDistinctFirstByIdAndIdOrderEquals...(Integer id, Integer idOrder);

//    @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
//    List<Article> fetchArticles(@Param("title") String title, @Param("category") String category);

}