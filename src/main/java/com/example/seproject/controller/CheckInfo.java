package com.example.seproject.controller;

import com.example.seproject.entity.User;
import com.example.seproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CheckInfo {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public int checkUserIdAndName(String id,String name){
		User user = userMapper.fineUserByIdAndName(id,name);
		if(user != null){
			return 1;
		}else
			return 0;
	}

	public int isMember(String type, String id, String passwd) {
		User user = userMapper.findUserByIdAndType(id, type);
		if (user != null && user.getPassword() != null) {
			if (passwordEncoder.matches(passwd, user.getPassword())) {
				return 1;
			} else {
				return 2;
			}
		}
		return 0;
	}
}