package com.zss.blog.handler;

import com.zss.blog.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 周书胜
 * @date 2023年04月20 22:08
 */
//对加了@Controller注解的方法进行拦截处理 AOP的实现
@ControllerAdvice
public class AllExceptionHandler {
    // 进行异常处理，处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回json数据
    public ResultVo doException(Exception ex) {
        ex.printStackTrace();
        return ResultVo.fail(-999, "系统异常");
    }
}
