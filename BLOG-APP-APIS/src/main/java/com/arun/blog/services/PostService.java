package com.arun.blog.services;

import java.util.List;

import com.arun.blog.entities.Post;
import com.arun.blog.payloads.PostDto;
import com.arun.blog.payloads.PostResponse;

public interface PostService {
	
	// create 
	
	PostDto createPost(PostDto postDto , Integer userId ,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto , Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	PostResponse getAllPost(Integer pageNumber , Integer pageSize ,String sortBy ,String sortDir);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by users
	List<PostDto> getPostByUser(Integer userId);
	
	// search Post by Id
	List<PostDto> searchPosts(String keyword);

}
