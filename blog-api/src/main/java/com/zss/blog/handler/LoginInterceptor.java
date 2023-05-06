package com.zss.blog.handler;

import com.alibaba.fastjson.JSON;
import com.zss.blog.dao.pojo.SysUser;
import com.zss.blog.service.LoginService;
import com.zss.blog.utils.UserThreadLocal;
import com.zss.blog.vo.ErrorCode;
import com.zss.blog.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 周书胜
 * @date 2023年04月22 11:20
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)){ // 请求方法必须是controller的方法
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if (token == null){
            ResultVo resultVo = ResultVo.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(resultVo));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            ResultVo resultVo = ResultVo.fail(ErrorCode.NO_LOGIN.getCode(), "未登录"); // token失效
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(resultVo));
            return false;
        }
        //是登录状态，放行
        // 希望在controller中 直接获取用户的信息
        UserThreadLocal.put(sysUser);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*
            用完记得remove掉sysUser，否则会造成内存泄露

            jvm:
                强引用，不会垃圾回收
                弱引用，每次垃圾回收会将其回收掉
                而ThreadLocal当线程结束时，会回收ThreadLocal和其存的key，
                但不会回收key对应的value，所以这个value就永远不会被回收，
                造成内存泄露
         */
        UserThreadLocal.remove();
    }
}
