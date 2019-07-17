package com.yang.springboot.code.controler;

import com.yang.springboot.code.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/07/17
 */
@RestController
public class Studentctrl {
    @Autowired
    StudentService studentService;

    @RequestMapping("/add")
    public String add (String name){
        return studentService.add(name)+" add success";
    }

    @RequestMapping("/del")
    public String del(int id){
        return studentService.del(id)+" del success";
    }

    @RequestMapping("/getall")
    public List getAll(){
        return studentService.getAll();
    }
}
