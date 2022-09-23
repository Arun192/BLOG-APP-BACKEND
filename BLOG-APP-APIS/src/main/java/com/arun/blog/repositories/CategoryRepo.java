package com.arun.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
