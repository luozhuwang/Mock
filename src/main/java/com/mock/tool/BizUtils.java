package com.mock.tool;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 通用业务工具
 * 普通工具类请优先使用{@link cn.hutool},{@link org.apache.commons.lang3}等
 *
 * @author vt
 * @since 2020/5/14
 */
@UtilityClass
public class BizUtils {

    /**
     * 处理url
     *
     * @param request url
     * @return 处理过的URL
     */
    public static String getProcessedUrl(HttpServletRequest request) {
        // get request url path
        String requestUrl = request.getRequestURI();
        // 空的话匹配 [/]
        requestUrl = StrUtil.isBlank(requestUrl) ? StrUtil.SLASH : requestUrl;
        return requestUrl;
    }

    /**
     * 包装请求参数为json
     *	params按顺序输出
     * @param request 请求
     * @return requestJson
     */
    @SneakyThrows
    public static String requestToJson(HttpServletRequest request) {    	
        JSONObject requestJsonObj = new JSONObject();
        // get all header
        Map<String, String> headerMap = ServletUtil.getHeaderMap(request);
        requestJsonObj.put("headers", headerMap);
        // get all param
        Map<String, String> paramMap = getParamMapUtil(request);
        requestJsonObj.put("params", paramMap);
        // body
        request = new ContentCachingRequestWrapper(request);
		try {
			@Cleanup
			BufferedReader reader = request.getReader();
			String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			requestJsonObj.put("body", body);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return requestJsonObj.toString();
    }
    /**
     * 将参数转化成MAP，顺序输出
     * */
    private  Map<String, String> getParamMapUtil(ServletRequest request) {
		Map<String, String> params = new LinkedHashMap<>();
		for (Map.Entry<String, String[]> entry : getParams(request).entrySet()) {
			params.put(entry.getKey(), ArrayUtil.join(entry.getValue(), StrUtil.COMMA));
		}
		return params;
	}
    
    /**
	 * 获得所有请求参数
	 * 
	 * @param request 请求对象{@link ServletRequest}
	 * @return Map
	 */
	private Map<String, String[]> getParams(ServletRequest request) {
		final Map<String, String[]> map = request.getParameterMap();
		return Collections.unmodifiableMap(map);
	}
}
