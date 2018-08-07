package com.dreawer.shopcenter.controller;

import com.dreawer.responsecode.rcdt.Error;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.ResponseCodeRepository;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * <code>BaseController</code> 它是本系统中所有控制器的基类，提供控制器通用方法的实现。
 * @author David Dai
 * @since Dreawer 1.0
 * @version 1.0
 */
public class BaseController {
    
    // --------------------------------------------------------------------------------
    // 其他
    // --------------------------------------------------------------------------------


    /**
     * 将指定（带空格的）关键词以 % 链接为关键词字符串。
     * @param keywords 关键词数组。
     * @return 关键词字符串。
     * @author David Dai
     * @since 2.0
     */
    protected String getKeyword(String keywords) {
        if (isBlank(keywords)) {
            return keywords;
        }
        return keywords.replace(" ", "%");
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
     * 获取request中的json参数。
     * @param req
     * @return
     * @throws IOException
     */
    protected String getRequestJson(HttpServletRequest req) throws IOException{
    	StringBuilder sb = new StringBuilder();
        BufferedReader in = req.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
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




    protected ResponseCode checkErrors(BindingResult result){
        return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(),result.getFieldError().getField(),Error.ENTRY);
    }
}
