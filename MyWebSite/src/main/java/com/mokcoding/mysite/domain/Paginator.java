package com.mokcoding.mysite.domain;

import lombok.Data;

@Data
public class Paginator {
	private String type;
	private String keyword;
	private int number = 0;
	private int size = 5;
}
