package com.example.seproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String birthday;
	private String institute;
	private String major;
	private String type;

}
