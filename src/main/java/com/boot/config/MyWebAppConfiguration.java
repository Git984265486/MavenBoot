package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class MyWebAppConfigurationextends implements WebMvcConfigurer {

    /**
     * 不需要登录拦截的url
     */
    final String[] notLoginInterceptPaths = {"/layui/**","/login","/index","/*.html","/chargePage"};

    /**【静态文件加载不到问题】**/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
         *这是图片的物理路径 "file:/+本地图片的地址"
         */   registry.addResourceHandler("/Path/**").addResourceLocations("file:/C:/CodeSoftware/Repository/gitRepository/hsProject/MavenBoot/src/main/resources/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**【拦截器】**/
    @Override
    public void addInterceptors(InterceptorRegistry registry ){

    }

    /**【允许跨域请求】**/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

}
