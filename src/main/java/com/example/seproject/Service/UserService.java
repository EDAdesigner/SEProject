package com.example.seproject.Service;

import com.example.seproject.entity.User;
import com.example.seproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean checkIfUserExists(String userType, String userId) {
        try {
            return userMapper.findUserByIdAndType(userId, userType) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteUser(String userType, String userId) {
        try {
            userMapper.deleteUserByIdAndType(userId, userType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting user from database", e);
        }
    }

    public boolean updateStudentInfo(String id, String password, String name, String gender, String birthDate, String institution, String major) {
        try {
            return userMapper.updateStudentInfo(id, password, name, gender, birthDate, institution, major) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTeacherInfo(String id, String password, String name, String gender, String birthDate, String institution, String major) {
        try {
            return userMapper.updateTeacherInfo(id, password, name, gender, birthDate, institution, major) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAdministratorInfo(String id, String password, String name, String gender, String birthDate, String institution, String major) {
        try {
            return userMapper.updateAdministratorInfo(id, password, name, gender, birthDate, institution, major) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findUserByIdAndType(String userId, String userType) {
        try {
            return userMapper.findUserByIdAndType(userId, userType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}