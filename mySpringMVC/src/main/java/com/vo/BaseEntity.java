package com.vo;

public abstract class BaseEntity<T> {

	private Page<T> page;

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}
	
	
}
