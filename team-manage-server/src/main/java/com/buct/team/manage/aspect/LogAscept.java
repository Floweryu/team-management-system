package com.buct.team.manage.aspect;

import com.buct.team.manage.annotation.Log;
import com.buct.team.manage.entity.SystemLog;
import com.buct.team.manage.service.SystemLogService;
import com.buct.team.manage.utils.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * @author Floweryu
 * @date 2021/5/23 14:36
 */
@Aspect
@Component
public class LogAscept {
    @Resource
    private SystemLogService systemlogService;
    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.buct.team.manage.annotation.Log)")
    public void pointcut() { }

    @AfterReturning("pointcut()")
    public void saveSysLog(JoinPoint joinPoint){

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();

        // 保存日志
        SystemLog systemLog = new SystemLog();

        MethodSignature signature = (MethodSignature)joinPoint.getSignature();

        Method method = signature.getMethod();

        Log logannotation = method.getAnnotation(Log.class);
        if (logannotation != null){
            String value = logannotation.value();
            systemLog.setDescription(value);   // 保存获取的操作
        }

        String url = request.getRequestURL().toString();
        systemLog.setUrl(url);

        String requestMethod = request.getMethod();
        systemLog.setRequestMethod(requestMethod);

        String query = request.getQueryString();
        systemLog.setRequestBody(query);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        systemLog.setRequestDate(currentTime);

        assert response != null;
        String status = String.valueOf(response.getStatus());
        systemLog.setReponseStatus(status);


        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        // 设置执行操作的用户名
        systemLog.setUserId(userId);
        systemlogService.addSystemLog(systemLog);
    }
}
