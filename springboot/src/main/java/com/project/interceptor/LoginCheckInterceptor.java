package com.project.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.project.pojo.Result;
import com.project.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
@CrossOrigin(origins = "http://8.134.116.81:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //处理预检请求
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,token");
            System.out.println("----------------------------");
            return false;
        }

        String url = request.getRequestURI();
        log.info("url:{}", url);

        String jwt = request.getHeader("token");
        System.out.println(jwt);

        if (!StringUtils.hasLength(jwt)) {
            log.info("token is empty");
            Result error = Result.error("NOT_LOGIN");

            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        } else {
            try {
                JWTUtils.parseJWT(jwt);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("parse error");
                Result error = Result.error("NOT_LOGIN");

                String notLogin = JSONObject.toJSONString(error);
                response.getWriter().write(notLogin);
                return false;
            }
        }

        log.info("parse success");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandler...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("aftercompletion...");
    }
}
