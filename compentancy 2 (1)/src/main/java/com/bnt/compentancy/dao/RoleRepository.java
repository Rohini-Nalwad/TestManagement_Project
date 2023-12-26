package com.bnt.compentancy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.compentancy.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
