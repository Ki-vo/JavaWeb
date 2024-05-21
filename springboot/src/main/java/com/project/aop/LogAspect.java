package com.project.aop;

import com.project.mapper.OperateLogMapper;
import com.project.pojo.OperateLog;
import com.project.pojo.Result;
import com.project.utils.IpUtils;
import com.project.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;

    @AfterReturning(value = "@annotation(com.project.anno.MyLog)", returning = "res")
    public void recordLog(JoinPoint joinPoint, Object res) throws Throwable {

        OperateLog result = new OperateLog();
        //记录操作的IP地址
        String ipAddress = IpUtils.getIpAddr(request);
        result.setIpAddress(ipAddress);
        //记录日志时间
        LocalDateTime now = LocalDateTime.now();
        result.setOperationTime(now);
        //获取请求要调用的方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("请求调用{}方法", methodName);
        //记录操作内容
        String operation = switch (methodName) {
            case "userLogin", "salesLogin", "adminLogin" -> "登录";
            case "exit" -> "登出";
            case "deregister" -> "注销";
            case "editPassword" -> "修改密码";
            case "addProduct" -> "添加商品";
            case "editProduct" -> "修改商品";
            case "delProductById" -> "删除商品";
            case "addSalesman" -> "添加销售人员";
            case "delSalesmanById" -> "删除销售人员";
            case "resetSalesPassword" -> "重置销售人员密码";
            case "deleteOperateLog" -> "删除操作日志";
            default -> "";
        };
        //忽略不符合操作定义的请求
        if (operation.isEmpty()) return;
        //记录操作内容
        result.setOperation(operation);

        //获取请求头中的jwt令牌
        String jwt = request.getHeader("token");
        //如果令牌为空，代表为登录操作
        if (!StringUtils.hasLength(jwt)) {
            //则从方法返回值获取jwt令牌并提取信息
            jwt = ((Result) res).getData().toString();
        }
        Claims claims = JWTUtils.parseJWT(jwt);
        log.info(claims.toString());
        //根据jwt中的"username""type"字段分辨操作角色：顾客、销售人员、管理员
        if (claims.containsKey("username")) {
            result.setRole("user");
            String userId = claims.get("username").toString();
            result.setUserId(userId);
        } else {
            String userId = claims.get("id").toString();
            result.setUserId(userId);
            if (claims.containsKey("type")) {
                result.setRole("sales");
            } else {
                result.setRole("admin");
            }
        }
        log.info("Operation Log:{}", result);
        operateLogMapper.addOperateLog(result);
    }
}