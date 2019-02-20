package com.mlooser.learn.refcassandra.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Table
@Data
@ToString
public class Person {

	@PrimaryKey	
	private String id;
	
	private String name;
	private Integer age;
		
	
	public Person() {
	}

	public Person(String id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}
