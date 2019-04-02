package com.mschmidl.jpademo.repository;

import com.mschmidl.jpademo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByRole(String role);

    @Query("SELECT f FROM Person f WHERE lower(f.role) = lower(:role)")
    List<Person> findByNameCaseInsensitive(@Param("role") String role);


    @Modifying
    @Query("UPDATE Person SET active = false WHERE id = :id")
    void setInactiveById(@Param("id") Long id);

}
