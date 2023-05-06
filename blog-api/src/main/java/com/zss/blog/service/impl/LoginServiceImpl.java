package com.zss.blog.service.impl;

import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zss.blog.dao.pojo.SysUser;
import com.zss.blog.service.LoginService;
import com.zss.blog.service.SysUserService;
import com.zss.blog.utils.JWTUtils;
import com.zss.blog.vo.ErrorCode;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author 周书胜
 * @date 2023年04月21 18:12
 */

@Service
public class LoginServiceImpl implements LoginService {

    private final static String slat = "mszlu!@#";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ResultVo login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return ResultVo.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        String pwd = DigestUtils.md5Hex(password + slat);
        SysUser sysUser = sysUserService.findUser(account, pwd);
        if (sysUser == null) {
            return ResultVo.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        // 登录成功，使用JWT生成token，返回token和redis中
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return ResultVo.success(token);
    }

    /**
     * 将token从redis中删除
     * @param token
     * @return
     */
    @Override
    public ResultVo logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return ResultVo.success(null);
    }

    @Override
    public ResultVo register(LoginParam loginParam) {
        /**
         * 1. 获取用户名，密码，nickname
         * 2. 检查参数是否合法
         * 3. 根据account查询用户，看是否已经存在
         * 4. 注册用户
         * 5. 保存该用户的token到redis
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();

        System.out.println("account=" + account);
        System.out.println("password=" + password);
        System.out.println("nickname=" + nickname);

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)
        || StringUtils.isBlank(nickname)) {
            return ResultVo.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if (sysUser != null) {
            return ResultVo.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password + slat));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        sysUserService.save(sysUser);

        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);

        return ResultVo.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        String user = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(user)) {
            return null;
        }
        SysUser sysUser = JSON.parseObject(user, SysUser.class);
        return sysUser;
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("admin" + slat));
    }
}
