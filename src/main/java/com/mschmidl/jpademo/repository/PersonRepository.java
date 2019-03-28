package com.mschmidl.jpademo.repository;

import com.mschmidl.jpademo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByRole(String role);

    @Query("SELECT f FROM Person f WHERE lower(f.name) = lower(:role)")
    List<Person> findByNameCaseInsensitive(String role);

}
