package com.yangyou.item.mapper;

import com.yangyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/10
 */
public interface BrandMapper extends Mapper<Brand> {

        /**
         * 新增商品分类和品牌中间表数据
         * @param cid 商品分类id
         * @param bid 品牌id
         * @return
         */
        @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
        int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

}