//package com.fivetwoff.hyonlinebe.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author VHBin
// * @date 2021/12/28 - 13:31
// */
//
//// 跨域访问
//
//@Configuration
//@EnableWebMvc
//@Slf4j
//public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
////                .allowedHeaders("*")
////                .allowCredentials(true)
////                .allowedMethods("*")
////                .allowedOrigins("http://101.132.145.198:3000, http://localhost:8080")
////                .allowedOrigins("*")
////                .exposedHeaders("token")
////                .maxAge(3600)
//        ;
//    }
//}
