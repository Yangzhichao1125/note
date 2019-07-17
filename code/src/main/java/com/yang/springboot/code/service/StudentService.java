package com.yang.springboot.code.service;

import com.yang.springboot.code.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/07/17
 */
@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public int add (String name){
        return studentDao.add(name);
    }

    public int del(int id){
        return studentDao.del(id);
    }

    public List getAll(){
        return studentDao.getAll();
    }



}
