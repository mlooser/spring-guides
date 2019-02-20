package com.mlooser.learn.refcassandra.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mlooser.learn.refcassandra.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String>{

}
