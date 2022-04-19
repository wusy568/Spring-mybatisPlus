package com.wu.mybatisplus.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @className: JWTUtils
 * @description: TODO 类描述
 * @author: sy
 * @date: 2022-03-30
 **/
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtils {

    //定义token返回头部
    public static String header;

    //token前缀
    public static String tokenPrefix;

    //签名密匙
    public static String secret;

    //有效期
    public static long expireTime;

    //存进客户端的token的key名
    public static final String USER_LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    public void setHeader(String header){
        JWTUtils.header = header;
    }

    public void setTokenPrefix(String tokenPrefix){
        JWTUtils.tokenPrefix = tokenPrefix;
    }

    public void setSecret(String secret){
        JWTUtils.secret = secret;
    }

    public void setExpireTime(Long expireTime){
        JWTUtils.expireTime = expireTime*1000L*60;
    }

    /**
     * @Author sy
     * @Description 创建token
     * @Date 14:44 2022-03-30
     * @Param sub
     * @return
    **/
    public static String createToken(String sub){
        return tokenPrefix + JWT.create()
                .withSubject(sub)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .sign(Algorithm.HMAC512(secret));
    }

    /**
     * @Author sy
     * @Description 验证token
     * @Date 14:50 2022-03-30
     * @Param token
     * @return
    **/
    /*public static String validateToken(String token){
        try {
            return JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix,""))
                    .getSubject();
        }catch (TokenExpiredException e){
            throw new ApiException(ResultInfo.unauthorized("token已经过期"));
        } catch (Exception e){
            throw new ApiException(ResultInfo.unauthorized("token验证失败"));
        }
    }*/

    /**
     * @Author sy
     * @Description 检查token是否需要更新
     * @Date 16:40 2022-03-31
     * @Param token
     * @return
    **/
    /*public static boolean isNeedUpdate(String token){
        //获取token过期时间
        Date expiresAt = null;
        try{
            expiresAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix,""))
                    .getExpiresAt();
        }catch (TokenExpiredException e){
            return  true;
        }catch (Exception e){
            throw new ApiException(ResultInfo.unauthorized("token验证失败"));
        }
        //如果剩余过期时间少于过期时长的一般时，需要更新
        return(expiresAt.getTime()-System.currentTimeMillis())<(expireTime>>1);
    }*/



}
