package cn.com.small_design.controller.filter;

import cn.com.small_design.common.common.UserInfo;
import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.common.utils.JwtUtil;
import cn.com.small_design.common.utils.RedisUtil;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author gejj
 * @create 2024年03月26日 11:20
 * @version 1.0
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //从请求头中获取token
        String token = request.getHeader("token");

        //请求无需登录
        if(!StringUtils.hasText(token)){
            chain.doFilter(request,response);
            return;
        }
        int userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = (int) claims.get("userId");
        } catch (Exception e) {
            //token解析超时、非法
            throw new BusinessException(GlobalExceptionEnums.TOKEN_PARSING_EXCEPTION);
        }

        if(Objects.isNull(userId)){
            throw new BusinessException(GlobalExceptionEnums.OBTAIN_USERID_ERROR);
        }

        UserInfo user = redisUtil.getCacheObject("login:" + userId);

        //验证是否存在登录用户
        if(Objects.isNull(user)){
            //用户登录过期，请重新登录
            throw new BusinessException(GlobalExceptionEnums.USER_LOGIN_TIMEOUT);
        }
        //将用户安全信息存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }
}
