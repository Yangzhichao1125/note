package com.yangyou.item.controllor;

import com.yangyou.common.pojo.PageResult;
import com.yangyou.item.pojo.SpuBo;
import com.yangyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/16
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询SPU
     * @param page
     * @param rows
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable",defaultValue = "false") Boolean saleable,
            @RequestParam(value = "key", required = false) String key) {
        // 分页查询spu信息
        PageResult<SpuBo> result = this.goodsService.querySpuByPageAndSort(page, rows, saleable,key);
        if (result == null || result.getItems().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}