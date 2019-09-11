package com.yang.springboot.code.controler;

import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/09/11
 */
@Controller
public class Index {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
