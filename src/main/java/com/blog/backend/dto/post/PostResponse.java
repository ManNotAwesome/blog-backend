package com.blog.backend.dto.post;

import java.time.LocalDateTime;

public class PostResponse {

	private Long id;
	private String title;
	private String content;
	private String authorEmail;
	private String categoryName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public PostResponse(Long id, String title, String content, String authorEmail, String categoryName,
			LocalDateTime createdAt, LocalDateTime updatedAt) {

		this.id = id;
		this.title = title;
		this.content = content;
		this.authorEmail = authorEmail;
		this.categoryName = categoryName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
