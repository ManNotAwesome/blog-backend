package com.blog.backend.dto.category;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryResponse {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryResponse(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
