package com.impacta.todo.resources.exceptions;

import java.io.Serializable;

import lombok.Data;

@Data
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long timeStamp;
	private Integer status;
	private String message;

	public StandardError() {
		super();
	}

	public StandardError(Long timeStamp, Integer status, String message) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.message = message;
	}

}
