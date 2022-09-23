package com.arun.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
