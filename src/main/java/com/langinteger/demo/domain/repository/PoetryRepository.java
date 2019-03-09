package com.langinteger.demo.domain.repository;

import com.langinteger.demo.domain.model.Poetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PoetryRepository extends JpaRepository<Poetry, Integer> {

  @Query(value = "select poet_id from poetries where id = ?1", nativeQuery = true)
  int findPoetIdByPoetryId(int poetryId);
}
