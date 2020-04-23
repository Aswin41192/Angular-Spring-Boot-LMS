package com.lms.dto;

import java.util.ArrayList;
import java.util.List;

public class MultiResponseDTO<T> extends PaginationDTO {

	private List<T> items = new ArrayList<T>();

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "MultiResponseDTO [items=" + items + "]";
	}
	
}
