package com.arun.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
