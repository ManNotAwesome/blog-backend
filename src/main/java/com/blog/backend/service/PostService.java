package com.blog.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.blog.backend.dto.post.CreatePostRequest;
import com.blog.backend.dto.post.PostResponse;
import com.blog.backend.dto.post.UpdatePostRequest;
import com.blog.backend.entity.Category;
import com.blog.backend.entity.Post;
import com.blog.backend.exception.ForbiddenException;
import com.blog.backend.exception.ResourceNotFoundException;
import com.blog.backend.repository.CategoryRepository;
import com.blog.backend.repository.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;

	public PostService(PostRepository postRepository, CategoryRepository categoryRepository) {
		this.postRepository = postRepository;
		this.categoryRepository = categoryRepository;
	}

	public PostResponse createPost(CreatePostRequest request, Authentication authentication) {

		String email = authentication.getName();

		Post post = new Post();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setAuthorEmail(email);

		if (request.getCategoryId() != null) {
			Category category = categoryRepository.findById(request.getCategoryId())
					.orElseThrow(() -> new ResourceNotFoundException("Category not found"));

		}

		Post saved = postRepository.save(post);

		return new PostResponse(saved.getId(), saved.getTitle(), saved.getContent(), saved.getAuthorEmail(),
				saved.getCategory() != null ? saved.getCategory().getName() : null, saved.getCreatedAt(),
				saved.getUpdatedAt());
	}

	public List<PostResponse> getAllPosts() {
		return postRepository.findAll().stream()
				.map(p -> new PostResponse(p.getId(), p.getTitle(), p.getContent(), p.getAuthorEmail(),
						p.getCategory() != null ? p.getCategory().getName() : null, p.getCreatedAt(), p.getUpdatedAt()))
				.toList();
	}

	public PostResponse getPostById(Long id) {

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

		return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getAuthorEmail(),
				post.getCategory() != null ? post.getCategory().getName() : null, post.getCreatedAt(),
				post.getUpdatedAt());
	}

	public PostResponse updatePost(Long id, UpdatePostRequest request, Authentication authentication) {

		String email = authentication.getName();

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

		if (!post.getAuthorEmail().equals(email)) {
			throw new ForbiddenException("You are not allowed to update this post!");

		}

		post.setTitle(request.getTitle());
		post.setContent(request.getContent());

		Post updated = postRepository.save(post);

		return new PostResponse(updated.getId(), updated.getTitle(), updated.getContent(), updated.getAuthorEmail(),
				updated.getCategory() != null ? updated.getCategory().getName() : null, updated.getCreatedAt(),
				updated.getUpdatedAt());
	}

	public void deletePost(Long id, Authentication authentication) {

		String email = authentication.getName();

		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

		if (!isAdmin && !post.getAuthorEmail().equals(email)) {
			throw new ForbiddenException("You are not allowed to delete this post!");

		}

		postRepository.delete(post);
	}

	public Page<PostResponse> getAllPosts(Pageable pageable) {

		return postRepository.findAll(pageable)
				.map(p -> new PostResponse(p.getId(), p.getTitle(), p.getContent(), p.getAuthorEmail(),
						p.getCategory() != null ? p.getCategory().getName() : null, p.getCreatedAt(),
						p.getUpdatedAt()));
	}

	public Page<PostResponse> getPostsByCategory(String categoryName, Pageable pageable) {

		return postRepository.findByCategory_Name(categoryName, pageable)
				.map(p -> new PostResponse(p.getId(), p.getTitle(), p.getContent(), p.getAuthorEmail(),
						p.getCategory() != null ? p.getCategory().getName() : null, p.getCreatedAt(),
						p.getUpdatedAt()));
	}

}
