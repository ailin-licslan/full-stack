package com.licslan.crudplay.dao;

import com.licslan.crudplay.entity.DataTestLicslan;
import com.licslan.crudplay.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataTestLicslanDao extends JpaRepository<DataTestLicslan,Long> {


    @Query("FROM DataTestLicslan c WHERE c.username = ?1")
    List<DataTestLicslan> findByUsername(String userName);
}
