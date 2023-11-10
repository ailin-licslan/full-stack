package com.licslan.crudplay.dao;

import com.licslan.crudplay.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PeopleDao extends JpaRepository<People,Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE People asu SET asu.delFlag = 1 WHERE asu.id=?1")
    Integer delLogic(Long id);

    List<People> findPeopleByName(String name);
}
