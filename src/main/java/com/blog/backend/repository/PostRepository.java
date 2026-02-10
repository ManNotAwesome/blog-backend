package com.blog.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.backend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Override
	Page<Post> findAll(Pageable pageable);

	Page<Post> findByCategory_Name(String name, Pageable pageable);

}
