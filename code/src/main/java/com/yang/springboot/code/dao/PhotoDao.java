package com.yang.springboot.code.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/09/12
 */
public interface PhotoDao {

    @Insert("insert into rm_photo (address,type,create_time,modify_time) VALUES(#{add},#{type},now(),now())")
    int addPhoto(String add,int type);

}
