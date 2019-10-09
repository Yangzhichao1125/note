package com.yang.springboot.code.controler;

import com.yang.springboot.code.been.RmPhoto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/09/18
 */
@RestController
public class GetImage {

    @RequestMapping("/getImage")
    public RmPhoto getImage(){
        RmPhoto rmPhoto = new RmPhoto();
        rmPhoto.setAddress("http://10.211.55.9:8888/group1/M00/00/00/CtM3CV1-SBCAdHEyAABYYDUgM-g135.jpg");
        rmPhoto.setCreateTime(new Date());
        rmPhoto.setHate(1);
        rmPhoto.setLove(12);
        rmPhoto.setType(1);
        return rmPhoto;

    }
}
