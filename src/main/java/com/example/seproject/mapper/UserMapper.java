package com.example.seproject.mapper;

import com.example.seproject.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{userId} AND type = #{userType}")
    User findUserByIdAndType(String userId, String userType);

    @Insert("INSERT INTO user (id, password, name, gender, birthday, institute, major, type) VALUES (#{id}, #{password}, #{name}, #{gender}, #{birthday}, #{institute}, #{major}, #{type})")
    void insertUser(User user);

    @Delete("DELETE FROM user WHERE id = #{userId} AND type = #{userType}")
    void deleteUserByIdAndType(String userId, String userType);

    @Update("UPDATE user SET password = #{password}, name = #{name}, gender = #{gender}, birthday = #{birthDate}, institute = #{institution}, major = #{major} WHERE id = #{id}")
    int updateStudentInfo(String id, String password, String name, String gender, String birthDate, String institution, String major);

    @Update("UPDATE user SET password = #{password}, name = #{name}, gender = #{gender}, birthday = #{birthDate}, institute = #{institution}, major = #{major} WHERE id = #{id}")
    int updateTeacherInfo(String id, String password, String name, String gender, String birthDate, String institution, String major);

    @Update("UPDATE user SET password = #{password}, name = #{name}, gender = #{gender}, birthday = #{birthDate}, institute = #{institution}, major = #{major} WHERE id = #{id}")
    int updateAdministratorInfo(String id, String password, String name, String gender, String birthDate, String institution, String major);
}
