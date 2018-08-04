package com.dreawer.shopcenter.web;

import com.dreawer.dream.util.MessageSourceUtils;
import com.dreawer.retail.exception.WxAppException;
import com.dreawer.retail.utils.HttpClientUtil;
import com.dreawer.user.utils.ParameterizedTypeImpl;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.dream.util.commons.StringUtils.isBlank;
import static com.dreawer.dream.util.commons.StringUtils.stringArray2String;
import static com.dreawer.retail.ControllerConstants.APP_DETAIL;
import static com.dreawer.retail.ControllerConstants.REFRESH_TOKEN;

/**
 * <code>BaseController</code> 它是本系统中所有控制器的基类，提供控制器通用方法的实现。
 * @author David Dai
 * @since Dreawer 1.0
 * @version 1.0
 */
public class BaseController extends com.dreawer.user.controller.BaseController {
    
    // --------------------------------------------------------------------------------
    // 其他
    // --------------------------------------------------------------------------------

    protected static final String HTTP_ACCOUNT_QUERY = MessageSourceUtils.getMessage("requestUrl", "account_query");

    protected static final String HTTP_LATEST_AUDIT_STATUS = MessageSourceUtils.getMessage("wxAppRequest", "latest_auditstatus_query");

    protected static final String HTTP_WXAPP_CATEGORY_QUERY = MessageSourceUtils.getMessage("wxAppRequest", "wxAppCategory_query");

    /**
     * 将指定（带空格的）关键词以 % 链接为关键词字符串。
     * @param keywords 关键词数组。
     * @return 关键词字符串。
     * @author David Dai
     * @since 2.0
     */
    protected String getKeyword(String keywords) {
        if (StringUtils.isBlank(keywords)) {
            return keywords;
        }
        return keywords.replace(" ", "%");
    }
    
    /**
     * 将指定关键词数组以 % 链接为关键词字符串。
     * @param keywords 关键词数组。
     * @return 关键词字符串。
     * @author David Dai
     * @since 2.0
     */
    protected String getKeyword(String[] keywords) {
        return stringArray2String(keywords, "%");
    }
    
    /**
     * 获取当前系统时间。
     * @return 当前系统时间。
     * @author David Dai
     * @since 2.0
     */
    protected Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 将json转换成对象集合。
     * @param json json对象
     * @param clazz 对象类型
     * @return 对象集合。
     * @author Kael
     * @since 2.0
     */
    protected <T> List<T> jsonToArray(String json, Class<T> clazz){
        if ((StringUtils.isBlank(json)) || (clazz == null)) {
            return null;
        }
        Type listType = new ParameterizedTypeImpl(List.class, new Class[] { clazz });
        List<T> list = new ArrayList<>();
        list = new Gson().fromJson(json, listType);
        return list;
    }
    
    /**
     * 获取request中的json参数。
     * @param req
     * @return
     * @throws IOException
     */
    protected String getRequestJson(HttpServletRequest req) throws IOException {
    	StringBuilder sb = new StringBuilder();
        BufferedReader in = req.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * http的post请求。
     * @param url 请求的地址。
     * @param params 请求的参数。
     * @return 返回结果。
     */
    protected String httpPost(String url, Map<String, String> params) {
        String result = null;
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(config);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        //遍历参数
        for(String key : params.keySet()){
            formparams.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                if(response.getStatusLine().getStatusCode()==200){
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        result = EntityUtils.toString(entity, "UTF-8");
                    }
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * http的get请求。
     * @param url 请求地址。
     * @param query 请求参数。
     */
    protected String httpGet(String url, String query) {
        String result = null;
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(url +"?"+ query);
            httpget.setConfig(config);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                if(response.getStatusLine().getStatusCode()==200){
                    // 获取响应实体
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        result =  EntityUtils.toString(entity);
                    }
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // @Test
    public String encrypt(String sSrc) throws Exception {
        // String sSrc = "060101012D1A683D48271A18316E471A";
        String sKey = "3A60432A5C01211F291E0F4E0C132825";

        // byte[] raw = sKey.getBytes("utf-8");
        byte[] databytes = parseHexStr2Byte(sSrc);
        byte[] keybytes = parseHexStr2Byte(sKey);
        SecretKeySpec skeySpec = new SecretKeySpec(keybytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(databytes);
        // String result = new Base64().encodeToString(encrypted);
        return parseByte2HexStr(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        // System.out.print(result);
    }

    //@Test
    public String decode(String text) throws Exception {
        // String text = "EC72214CB09CD11B52FE73E901D8DC48";
        // String text = "D23331C277810CDBD096AF26DFF12D6D";
        String sKey = "3A60432A5C01211F291E0F4E0C132825";

        byte[] databytes = parseHexStr2Byte(text);
        byte[] key = parseHexStr2Byte(sKey);

        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");//"算法/模式/补码方式"
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
        byte[] data = cipher.doFinal(databytes);
        // String result = new Base64().encodeToString(data);
        return parseByte2HexStr(data);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        // System.out.print(result);
    }

    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 检查AccessToken是否已经过期
     * @param appId 小程序Appid
     * @return  当前可用的Token
     * @throws WxAppException
     */
    public String checkTokenAvailability(String appId) throws WxAppException, JSONException {
        Map<String, String> param = new HashMap<>();
        param.put("appid", appId);
        String accountResponse = HttpClientUtil.doGet(HTTP_ACCOUNT_QUERY + APP_DETAIL, param);
        if (accountResponse.isEmpty()) {
            throw new WxAppException("account系统异常!");
        }
        JSONObject json = new JSONObject(accountResponse);
        //判断返回结果
        if (!json.has("status") || !json.getBoolean("status")) {
            throw new WxAppException("appId无效!");
        }
        JSONObject data = json.getJSONObject("data");
        String accessToken = data.getString("accessToken");
        String response = HttpClientUtil.doGet(HTTP_LATEST_AUDIT_STATUS + "?access_token=" + accessToken);
        if (response.isEmpty()) {
            throw new WxAppException("系统异常,请稍后再试!");
        }
        JSONObject auditResp = new JSONObject(response);
        if (auditResp.getInt("errcode") == 42001 || auditResp.getInt("errcode") == 40001) {
            //token过期,重新刷新token
            String tokenResponse = HttpClientUtil.doGet(HTTP_ACCOUNT_QUERY + REFRESH_TOKEN + "?appid=" + appId);
            if (tokenResponse.isEmpty()) {
                throw new WxAppException("Token刷新失败");
            }
            JSONObject tokenJson = new JSONObject(tokenResponse);
            if (tokenJson.get("status").equals(false)) {
                throw new WxAppException("Token刷新失败");
            }
            if (tokenJson.get("status").equals(true)) {
                accessToken = tokenJson.get("data").toString();
            }
        }
        return accessToken;
    }

    /**
     * 获取授权小程序帐号的可选类目 (请确保accessToken已刷新)
     *
     * @param accessToken
     * @return jsonArray
     */
    protected JSONArray getCategory(String accessToken) throws JSONException, WxAppException {
        String response = HttpClientUtil.doGet(HTTP_WXAPP_CATEGORY_QUERY + "?access_token=" + accessToken);
        JSONObject jsonObject = new JSONObject(response);
        if (!jsonObject.has("errcode") || jsonObject.get("errcode").equals(-1)) {
            throw new WxAppException("微信类目系统异常");
        }
        if (jsonObject.has("errcode") && jsonObject.get("errcode").equals(48001)) {
            throw new WxAppException("48001", "用户未提供权限信息");
        }
        if (jsonObject.get("errcode").equals(41000)) {
            //token超时
            throw new WxAppException("微信类目系统异常");
        }
        //其他错误
        if (!jsonObject.get("errcode").equals(0)) {
            throw new WxAppException(jsonObject.get("errmsg").toString());
        }
        JSONArray category_list = jsonObject.getJSONArray("category_list");
        return category_list;
    }
}
