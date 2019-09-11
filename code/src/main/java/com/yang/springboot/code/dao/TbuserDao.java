package com.yang.springboot.code.dao;

import com.yang.springboot.code.been.Tbuser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/09/11
 */
public interface TbuserDao {

    @Select("SELECT id,username,password FROM tbuser")
    List<Tbuser> getAll();
}
