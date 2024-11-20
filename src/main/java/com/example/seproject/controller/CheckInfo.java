package com.example.seproject.controller;

import com.example.seproject.entity.User;
import com.example.seproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class CheckInfo {
	/*
	 * 登陆时检查用户信息
	 */

	@Autowired
	private UserMapper userMapper;

	public int isMember(String table, String id, String passwd) {
		User user = userMapper.findUserByIdAndType(id, table);
		if (user != null) {
			if (user.getPwd().equals(passwd)) {
				return 1;
			} else {
				return 2;
			}
		}
		return 0;
	}
}
