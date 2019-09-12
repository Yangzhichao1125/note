package com.yang.springboot.code.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * download images
 *
 * @author yang
 * @date 2019/09/12
 */
public class DownLoad {


    public static void main(String[] args) {
        DownLoad downLoad = new DownLoad();
        String url = "http://image.baidu.com/"; // url 网站首页 
        boolean flag = true;          // flag 是否下载域名外的图片 
        downLoad.downLoadAllImg(url,flag);
    }
  /**
       * 获取网页中所有网页链接
       * @param url
       * @param isAll 默认为false 查询网址开头的链接, true 查询所有链接 包括外部链接
       * @return
       */
    public Set<String> getLink(String url, Boolean isAll) {
        Document doc = null;
        Set<String> set = new HashSet<String>();
        try {
            doc = Jsoup.connect(url).ignoreContentType(true).timeout(5000).header("Referer", url).header("User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                    .get();
        } catch (IOException e) {
            System.out.println("无法连接" + url);
            return set;
        }
        Elements as = doc.select("a[href]");
        for (Element a : as) {
            String href = a.attr("abs:href").replaceAll("#", "");
            if(!isHttpUrl(href))
            continue;
            if(isAll) {
                set.add(href);
            }else if(href.indexOf(url) != -1 ){
                set.add(href);
            }
            System.out.println(href);
        }
        return set;
    }


    /**
     * 获取域名下所有网页链接
     * @param url
     * @return
     */
    public void downLoadAllImg(String url,boolean flag) {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        Set<String> allSet = new HashSet<String>();
        Set<String> oldSet = new HashSet<String>();
        allSet.add(url);
        map.put("allSet", allSet);
        map.put("oldSet", oldSet);
        exclude(map,flag).get("allSet");
    }


    /**
     * 获取网页中的图片
     * @param url 网页地址
     * @param directory 图片保存路径
     */
    public void downLoadImg(String url,String directory) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).ignoreContentType(true).timeout(5000).header("Referer", url).header("User-Agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                    .get();
        } catch (IOException e) {
            System.out.println("连接网页" + url +"失败");
            return;
        }
        Elements imgs = doc.select("img");
        for (Element img : imgs) {
            String alt = img.attr("alt");
            String link = "";
            String data_lazy_src = img.attr("abs:data-lazy-src");
            if (data_lazy_src != null && !data_lazy_src.isEmpty()) {
                link = data_lazy_src;
            } else {
                String data_src = img.attr("abs:data-src");
                if (data_src != null && !data_src.isEmpty()) {
                    link = data_src;
                } else {
                    link = img.attr("abs:src");
                }
            }
            if (link != null && !link.isEmpty()) {
                System.out.println(alt + ":" + link);
                downLoad(link, directory);
            }
        }
    }



    /**
     * 查询域名下所有网页链接的核心算法(递归)
     * @param map key="allSet" 的 value 所有网页链接
     *      key="oldSet" 的 value 解析过的网页链接
     *      allSet和oldSet 进行对比,不相同就一直查
     * @return
     */
    private Map<String, Set<String>> exclude(Map<String, Set<String>> map,boolean flag) {
        Set<String> allSet = map.get("allSet");
        Set<String> oldSet = map.get("oldSet");
        if (allSet.equals(oldSet)) {
            return map;
        } else {
            Set<String> newSet = new HashSet<String>(allSet);
            newSet.removeAll(oldSet);
            for (String url : newSet) {
                downLoadImg(url, "/Users/yang/Pictures/down/");
                oldSet.add(url);
                Set<String> set = getLink(url,flag);
                allSet.addAll(set);
            }
            map.put("allSet", allSet);
            map.put("oldSet", oldSet);
            exclude(map,flag);
        }
        return map;
    }

    /**
     * 下载文件
     * @param link    文件HTTP链接
     * @param directory 文件本地保存目录
     */
    public void downLoad(String link, String directory) {
        URLConnection conn = null;
        try {
            conn = new URL(link).openConnection();
            conn.setRequestProperty("Referer", link);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
            conn.connect();
        } catch (IOException e) {
            System.out.println("连接" + link +"失败");
            return;
        }
        File file = new File(directory);
        if(!file.exists()) {
		 file.mkdirs();
        }
        String fileType = link.substring(link.lastIndexOf("."), link.length());
        long time = System.currentTimeMillis();
        String fileName = directory + Long.toString(time) + fileType;
        try {
            if (conn.getContentLength() < 1000)
                return;
            InputStream in = new BufferedInputStream(conn.getInputStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));
            byte[] bytes = new byte[1024 * 8];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.flush();
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("下载到" + fileName + "失败");
            return;
        }
    }
	
  /**
   * 判断网址格式是否正确
   * @param urls
   * @return
   */
          public boolean isHttpUrl(String urls) {
    boolean isurl = false;
    String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
      + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";
    Pattern pat = Pattern.compile(regex.trim());
    Matcher mat = pat.matcher(urls.trim());
    isurl = mat.matches();
    if (isurl) {
      isurl = true;
    }
    return isurl;
  }


} 
