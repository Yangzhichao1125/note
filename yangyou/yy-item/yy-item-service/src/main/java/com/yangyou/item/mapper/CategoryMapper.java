package com.yangyou.item.mapper;

import com.yangyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/10
 */
public interface CategoryMapper extends Mapper<Category> , SelectByIdListMapper<Category, Long> {
}
