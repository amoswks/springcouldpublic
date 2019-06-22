package com.cloud.eurekaclient.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Slf4j
public class HttpUtil {
    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return
     */
    public static String sendGet(String url,String param){
        String result = "";
        BufferedReader br = null;
        try{
            String urlNameString = url + (StringUtils.isNotEmpty(param) ? "?" + param : "");
            URL requesturl = new URL(urlNameString);
            //打开url与URL之间的连接
            URLConnection connection = requesturl.openConnection();
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立实际连接
            connection.connect();
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = br.readLine()) != null){
                result += line;
            }


        }catch (Exception e){
            log.error("Get请求发送异常！");
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                log.error("关闭输入流异常");
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url){
       return sendGet(url,"");
    }

    public static String sendGet(String url,Map<String,String> params){
        return sendGet(url,paramString(params));
    }

    /**
     * map转换为toString
     * @param param
     * @return
     */
    public static String paramString(Map<String,String> param){
        if (param == null || param.isEmpty()){
            return null;
        }
        Iterator<Map.Entry<String,String>> it = param.entrySet().iterator();
        StringBuffer br = new StringBuffer();
        for (int i = 0; it.hasNext();i++){
            if (i == param.size()-1){
                Map.Entry<String,String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                br.append(key).append("=").append(value);
            }else {
                Map.Entry<String,String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                br.append(key).append("=").append(value).append("&");
            }
        }
        return br.toString();
    }
    /**
     * 发送post请求
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url,String param,String contentType){
        StringBuffer result = new StringBuffer();
        PrintWriter printWriter = null;
        BufferedReader br = null;
        try{
            URL realURL = new URL(url);
            HttpURLConnection connection  = (HttpURLConnection) realURL.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(5000);

            printWriter = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));

            // 发送请求参数
            printWriter.print(param);
            // flush输出流的缓冲
            printWriter.flush();

            //建立实际的连接
            connection.connect();
            if (connection.getResponseCode() == 200){
                Map<String, List<String>> head = connection.getHeaderFields();
                System.out.println(head);

                //获得请求正文
                String line = "";
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while ((line = br.readLine()) != null){
                    result.append(line);
                }
            }else {
                return "返回错误参数:" + connection.getResponseCode();
            }


        }catch (Exception e){
            log.error("post请求失败！");
            e.printStackTrace();
        }finally {
            try {
                printWriter.close();
                br.close();
                log.error("poat请求关闭失败");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }


}
