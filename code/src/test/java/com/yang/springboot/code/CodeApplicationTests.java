package com.yang.springboot.code;

import com.yang.springboot.code.dao.TbuserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeApplicationTests {

    @Autowired
    TbuserDao tbuserDao;


    @Test
    public void contextLoads() {
        System.out.println(tbuserDao.getAll());
    }

}
