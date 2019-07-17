package com.yang.springboot.code.dao;

import com.yang.springboot.code.been.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/07/17
 */
@Repository
@Mapper
public interface StudentDao {

    //增
    @Insert("INSERT INTO student (NAME) VALUES (#{name})")
    int add(String name);
    //删
    @Delete("delete from student where id = #{id}")
    int del(int id);
    //改
    int update(Student student);
    //查

    Student get(int id);
    //所有
    @Select("SELECT * FROM student")
    List<Student> getAll();

}
