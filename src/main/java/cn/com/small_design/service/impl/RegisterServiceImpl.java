package cn.com.small_design.service.impl;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.controller.base.dto.RegisterDto;
import cn.com.small_design.dao.dao.UserMapper;
import cn.com.small_design.dao.dao.pojo.User;
import cn.com.small_design.service.IRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author gejj
 * @createTime 2024年04月09日 16:33
 * @version 1.0
 */

@Service
public class RegisterServiceImpl implements IRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public RestResponse<?> register(RegisterDto registerDto) {
        //验证用户信息是否存在
        User user = userMapper.queryUserByUsername(registerDto.getLoginName());

        if(!Objects.isNull(user)){
            logger.info("该用户名已存在，请重新输入登录用户名！");
            throw new RuntimeException("该用户名已存在，请重新输入登录用户名！");
        }
        //对密码进行加密
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        //保存用户
        userMapper.addUser(registerDto);
        return null;
    }
}