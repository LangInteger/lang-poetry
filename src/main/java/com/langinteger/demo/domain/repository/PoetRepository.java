package com.langinteger.demo.domain.repository;

import com.langinteger.demo.domain.model.Poet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoetRepository extends JpaRepository<Poet, Integer> {

  List<Poet> findByNameLike(String name);
}
