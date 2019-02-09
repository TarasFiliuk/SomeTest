package com.kindgeek.test.repository;

import com.kindgeek.test.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Integer> {
}
