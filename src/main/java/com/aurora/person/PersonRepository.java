package com.aurora.person;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by atif on 2/3/17.
 */
public interface PersonRepository extends JpaRepository<Person, Long>
{
    @Query("SELECT u FROM Person u WHERE u.firstName LIKE CONCAT('%',:name,'%') or u.lastName LIKE CONCAT('%',:name,'%') order by u.firstName")
    List<Person> getAllByName(@Param("name") String name, Pageable pageable);
}
