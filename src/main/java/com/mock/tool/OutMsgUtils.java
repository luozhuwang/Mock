package com.mock.tool;

import lombok.Cleanup;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 直接通过response写出
 *
 * @author vt
 * @since 2020-5-14
 */
public class OutMsgUtils {

    /**
     * 没找到url的情况
     *
     * @param response
     */
    public static void mockUrlNotFond(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        outMsg("SYSTEM_MESSAGE：该路径未在系统中配置，请在[接口一览]模块进行配置。", response);
    }

    /**
     * 没找到response的情况
     *
     * @param response
     */
    public static void notFondResponse(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        outMsg("SYSTEM_MESSAGE：系统存在该路径，但是还没有启用一个返回体，请与[接口一览 > 返回体]模块进行配置。", response);
    }


    /**
     * 没找到response的情况
     *
     * @param response
     */
    public static void notFondResponseRestful(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        outMsg("SYSTEM_MESSAGE：系统存在该路径，但是该HTTP方法请求下还没有配置返回体，请与[接口一览 > 返回体]模块进行配置。", response);
    }

    /**
     * 方法错误
     *
     * @param response
     */
    public static void methodNotValid(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        outMsg("SYSTEM_MESSAGE：HTTP请求方式与系统中配置不匹配。", response);
    }

    /**
     * 发射消息
     *
     * @param response 响应
     */
    @SneakyThrows
    public static void outMsg(String msg, HttpServletResponse response) {
    	response.setContentType("text/html;charset=utf-8");
    	@Cleanup  PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
    }
}
