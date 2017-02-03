package com.aurora.person;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by atif on 2/3/17.
 */
public interface PersonRepository extends JpaRepository<Person, Long>
{
}
