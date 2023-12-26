package com.bnt.compentancy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.compentancy.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
