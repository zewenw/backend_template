package com.backend.oauth.utils;

import com.backend.common.dto.ResultDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 结果封装类
 */
public class ResponseUtils {

    public static void result(HttpServletResponse response, ResultDTO msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(msg).getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
