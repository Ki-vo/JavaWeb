package com.project;

import com.project.controller.LoginController;
import com.project.mapper.UserMapper;
import com.project.pojo.Result;
import com.project.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class SpringbootApplicationTests {


    @Test
    public void test() {
        String path=System.getProperty("user.dir")+"\\image";
        System.out.println(path);
    }

//    @Test
//    public void testGenJwt() {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", 1);
//        claims.put("name", "tom");
//
//        String jwt = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256, "itheima")
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
//                .compact();
//        System.out.println(jwt);
//    }

//    @Test
//    public void testParseJwt() {
//        Claims claims = Jwts.parser()
//                .setSigningKey("itheima")
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcwMTE3MTI5OX0.Umvxx2rEl4U5Q9YrbvJdCNK9AkxmqJ9d_k01KksM2Ew")
//                .getBody();
//
//        System.out.println(claims);
//    }


}
