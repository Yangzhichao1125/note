package com.yangyou.upload.service;

import com.yangyou.upload.controller.UploadController;
import com.yangyou.upload.util.OssUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/10
 */
@Service
public class UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    // 支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    @Autowired
    OssUtil ossUtil;

    public String upload(MultipartFile file) {
        try {
            // 1、图片信息校验
            // 1)校验文件类型
            String type = file.getContentType();
            if (!suffixes.contains(type)) {
                logger.info("上传失败，文件类型不匹配：{}", type);
                return null;
            }
            // 2)校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                logger.info("上传失败，文件内容不符合要求");
                return null;
            }
            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            // 2.3、图片地址
            String url = ossUtil.uploadFile(fileType, file);

//            String url = "http://image.leyou.com/upload/" + file.getOriginalFilename();
            logger.info("图片地址： "+url);
            return url;
        } catch (Exception e) {
            return null;
        }
    }
}