package com.lanxi.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


/**
 * HttpServletRequest辅助工具
 *
 * @author wxc
 *
 */
public class RequestUtil {

    private static Logger log = Logger.getLogger(RequestUtil.class);
    /**
     * 获取Request请求所有参数
     * @return
     * @throws IOException
     */
    public static Map<String, String> getFromsRequest(HttpServletRequest request)
            throws IOException {
        if ("POST".equals(request.getMethod())) {

            return getParamsPost(request.getInputStream());//post传的值
        }
        Enumeration<?> names = request.getParameterNames();
        Map<String, String> params = new HashMap<String, String>();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            params.put(name, getFromRequest(request, name));
        }
        return params;
    }

    public static String getFromRequest(HttpServletRequest request, String name) {
        String val = request.getParameter(name);
        return val != null ? val.trim() : null;
    }

    public static Map<String, String> getParamsPost(InputStream is)
            throws IOException {
        Map<String, String> temp = new HashMap<String, String>();
        String[] params = inputStreamToString(is).split("\\&");//将传入的值拆分成数组
        if (params.length > 1) {
            for (int i = 0; i < params.length; ++i) {
                String param = params[i];
                int idx = param.indexOf("=");
                String value = param.substring(idx + 1, param.length());
                if (value != null) {
                    //进行url解码
                    value = URLDecoder.decode(value,"UTF-8");
                }
                temp.put(param.substring(0, idx), value);
            }
        }
        return temp;
    }

    public static String inputStreamToString(InputStream is) {
        BufferedReader in = null;
        StringBuffer sb = null;
        try {
            sb = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);//拼接传入的值
                System.out.println("读取"+line);
            }
            log.info("接受参数："+URLDecoder.decode(sb.toString(),"UTF-8"));
            return URLDecoder.decode(sb.toString(),"UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e.getClass().getName() + ":"
                    + e.getMessage(), e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
