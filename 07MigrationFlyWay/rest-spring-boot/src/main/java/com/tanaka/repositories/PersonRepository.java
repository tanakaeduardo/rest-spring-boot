package com.tanaka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanaka.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
