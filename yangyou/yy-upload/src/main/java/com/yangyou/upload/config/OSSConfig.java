package com.yangyou.upload.config;


/**
 * This is Description
 *
 * @author yang
 * @date 2019/12/11
 */
public class OSSConfig {

    private  String endpoint;          //连接区域地址
    private  String accessKeyId;      //连接keyId
    private  String accessKeySecret;    //连接秘钥
    private  String bucketName;      //需要存储的bucketName
    private  String picLocation;      //图片保存路径

    public OSSConfig() {
            this.endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
            this.bucketName = "yang-photo";
            this.picLocation = "images/";
            this.accessKeyId = "LTAI4FunzsPtrpqjeDzDE3h7";
            this.accessKeySecret = "JZXWMaBs0de3FjmKPrJwf76iNN3CG0";
    }


    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    public String getAccessKeySecret() {
        return accessKeySecret;
    }
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
    public String getBucketName() {
        return bucketName;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getPicLocation() {
        return picLocation;
    }
    public void setPicLocation(String picLocation) {
        this.picLocation = picLocation;
    }

}
