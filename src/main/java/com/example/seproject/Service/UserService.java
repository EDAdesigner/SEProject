package com.example.seproject.Service;

import com.example.seproject.entity.User;
import com.example.seproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean checkIfUserExists(String userType, String userId) {
        try {
            return userMapper.findUserByIdAndType(userId, userType) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkUserIdAndName(String userid, String userName){
        try {
            return userMapper.fineUserByIdAndName(userid,userName)!=null;
        } catch (Exception e){
            e.printStackTrace();;
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
            return userMapper.updateStudentInfo(id, encode(password), name, gender, birthDate, institution, major) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTeacherInfo(String id, String password, String name, String gender, String birthDate, String institution, String major) {
        try {
            return userMapper.updateTeacherInfo(id, encode(password), name, gender, birthDate, institution, major) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAdministratorInfo(String id, String password, String name, String gender, String birthDate, String institution, String major) {
        try {
            return userMapper.updateAdministratorInfo(id, encode(password), name, gender, birthDate, institution, major) > 0;
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

    public User fineUserByIdAndName(String userId, String userName){
        try {
            return userMapper.fineUserByIdAndName(userId, userName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String encode(String code){
        String encodedcode = passwordEncoder.encode(code);
        return encodedcode;
    }
}