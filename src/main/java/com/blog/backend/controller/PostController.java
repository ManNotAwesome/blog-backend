package com.blog.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.backend.dto.post.CreatePostRequest;
import com.blog.backend.dto.post.PostResponse;
import com.blog.backend.dto.post.UpdatePostRequest;
import com.blog.backend.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest request,
			Authentication authentication

	) {
		return ResponseEntity.ok(postService.createPost(request, authentication));

	}

	@GetMapping
	public ResponseEntity<Page<PostResponse>> getAllPosts(Pageable pageable) {
		return ResponseEntity.ok(postService.getAllPosts(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @Valid @RequestBody UpdatePostRequest request,
			Authentication authentication) {
		return ResponseEntity.ok(postService.updatePost(id, request, authentication));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Long id, Authentication authentication) {
		postService.deletePost(id, authentication);
		return ResponseEntity.ok("Post deleted successfully!");
	}

	@GetMapping("/category/{name}")
	public ResponseEntity<Page<PostResponse>> getPostsByCategory(@PathVariable String name, Pageable pageable) {
		return ResponseEntity.ok(postService.getPostsByCategory(name, pageable));
	}

}
