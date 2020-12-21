package com.mock.tool;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
/**
 * 字符集工具类
 * */
public class characterTools {

	/**URL进行解码*/
	public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
	/**URL进行编码*/
	public static String getURLencodeString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result =URLEncoder.encode( str, "UTF-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
