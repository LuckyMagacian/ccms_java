package com.lanxi.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;


public class HttpUtils {


    /**
     * 通过URL POST的方式获取JSON数据
     * @param _url	接口地址
     * @param _params	传递参数
     * @param _charSet	字符编码
     * @return jsonData
     */
    public static final String getJsonByPost(String _url, Map<String, String> _params, String _charSet) {
        if (_charSet == null || _charSet.length() == 0){
            _charSet = "utf-8";
        }
        String jsonData = "";
        try {
            URL httpurl = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
            if(_params!=null){
                conn.setDoOutput(true);//设置连接允许发送数据
                conn.setDoInput(true);//设置连接允许接收数据
                // Set the post method. Default is GET
                conn.setRequestMethod("POST");
                // Post 请求不能使用缓存
//				conn.setUseCaches(false);
                PrintWriter out = new PrintWriter((new OutputStreamWriter(conn.getOutputStream(), _charSet)));
                int i = 0;
                //处理post中发送的参数
                Set<Map.Entry<String, String>> set = _params.entrySet();
                for (Map.Entry<String, String> entry:set){
                    out.print(entry.getKey());
                    out.print("=");
                    out.print(entry.getValue());
                    if (i!=set.size()-1){
                        out.print("&");
                    }
                    i++;
                }
                out.flush();
                out.close();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), _charSet));
            StringBuffer result = new StringBuffer();//存放结果
            String line;//存放读取单行拿到的数据
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            jsonData = result.toString();
            in.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException("发送post请求出错,url:" + _url,e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的字符集,charSet:" + _charSet,e);
        } catch (IOException e) {
            throw new RuntimeException("发送post请求IO出错,url:" + _url,e);
        }
        return jsonData ;
    }

//    public static void main(String[] args) throws Exception {
//        String url = "http://localhost:8000/shortMessage/sendMessage.do";
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("mchtId", "22");
//        map.put("orderId", "22"+DateTimeUtil.getTime()+"0000");
//        map.put("mobile", "15268828266");
//        map.put("content", "测试");
//        map.put("tradeDate", "20160911");
//        map.put("tradeTime", "111111");
//        map.put("tdId", "1");
//        String signStr = SignUtil.sortParamsToSign(map) + "df2330e1257c457a";
//        map.put("sign",  DigestUitl.MD5LowerCase(signStr));
//        String result = getJsonByPost(url,map,"utf-8");
//        System.out.println("result="+result);
//    }
}
